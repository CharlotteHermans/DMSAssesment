package nl.svb.dms.assessment.customer.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.svb.dms.api.leaseacar.LeaseControllerApi;
import nl.svb.dms.assessment.customer.domain.Customer;
import nl.svb.dms.assessment.customer.exception.CustomerException;
import nl.svb.dms.model.leaseacar.LacLeaseRateResponseDto;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
@Log4j2
public class LeaseAdapter {
    private static final long RETRY_MAX_ATTEMPTS = 3L;
    private static final long RETRY_DELAY_IN_MS = 1000;
    private static final String TIMEOUT_RETRY_OVERSCHREDEN = "Service tijdelijk niet beschikbaar: aantal timeout-retry overschreden";

    private final LeaseControllerApi leaseApi;

    public BigDecimal retrieveLeaseRate(Customer customer) {
        try {
            log.info("Call naar lease api");

            ResponseEntity<LacLeaseRateResponseDto> resp = leaseApi.getLeaseRateWithHttpInfo(customer.getMake(), customer.getModel(), customer.getVersion(), getCorrelationId(), customer.getDuration(), customer.getMileage())
                    .retryWhen(Retry.backoff(RETRY_MAX_ATTEMPTS, Duration.ofMillis(RETRY_DELAY_IN_MS))
                            .filter(SocketTimeoutException.class::isInstance)
                            .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                                throw new CustomerException(TIMEOUT_RETRY_OVERSCHREDEN, HttpStatus.SERVICE_UNAVAILABLE);
                            }))
                    .block();
            if (resp != null && resp.getBody() != null) {
                return resp.getBody().getLeaseRate();
            } else {
                log.info("No lease rate");
                throw new CustomerException("No lease rate", HttpStatus.NOT_FOUND);
            }

        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new CustomerException("bad request" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CustomerException("Data not found", HttpStatus.NOT_FOUND);
            } else if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                throw new CustomerException("Internal server error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//            }else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED){
//                throw new CustomerException(BUDGETHOUDER_UNAUTHORIZED + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                throw new CustomerException("Unexpected error" + e.getStatusCode().value() + " " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Haal het CorrelationId op uit MDC.
     * Deze call is in een aparte method gezet ivm unit-test testbaarheid
     *
     * @return {@link String} correlationId
     */
    protected String getCorrelationId() {
        return MDC.get("CID");
    }

}
