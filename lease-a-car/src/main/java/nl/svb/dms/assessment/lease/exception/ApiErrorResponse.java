package nl.svb.dms.assessment.lease.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.Map;

/**
 * Api Error response POJO.
 */
@Getter
@EqualsAndHashCode
@Schema(description = "Generieke Error Message")
@Log4j2
public class ApiErrorResponse {

    @Schema(description = "HTTP error code", accessMode = Schema.AccessMode.READ_ONLY)
    private final int status;
    @Schema(description = "HTTP error naam", accessMode = Schema.AccessMode.READ_ONLY)
    private final String titel;
    @Schema(description = "Error omschrijving", accessMode = Schema.AccessMode.READ_ONLY)
    private final String detail;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "Error lijst", accessMode = Schema.AccessMode.READ_ONLY)
    private Map<String, List<String>> errors;


    /**
     * ApiErrorResponse constructor met validatiefouten.
     *
     * @param status status
     * @param titel  titel
     * @param detail detail
     * @param errors validation errors
     */
    public ApiErrorResponse(int status, String titel, String detail,
                            Map<String, List<String>> errors) {
        this.status = status;
        this.titel = titel;
        this.detail = detail;
        this.errors = errors;
    }

    /**
     * ApiErrorResponse constructor.
     *
     * @param status status
     * @param titel  titel
     * @param detail detail
     */
    public ApiErrorResponse(int status, String titel, String detail) {
        this.status = status;
        this.titel = titel;
        this.detail = detail;
    }

    /**
     * ApiErrorResponse constructor.
     * detail will be the same value as titel.
     *
     * @param status status
     * @param titel  titel
     */
    // op 1 constructor is deze annotatie nodig om via ObjectMapper String om te kunnen zetten naar ApiErrorResponse
    // bv new ObjectMapper().readValue(contentAsString, ApiErrorResponse.class);
    @ConstructorProperties({"status", "titel"})
    public ApiErrorResponse(int status, String titel) {
        this.status = status;
        this.titel = titel;
        this.detail = titel;
    }

    /**
     * ApiErrorResponse constructor for HttpStatus.
     *
     * @param httpStatus HttpStatus.
     */
    public ApiErrorResponse(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.titel = httpStatus.getReasonPhrase();
        this.detail = httpStatus.getReasonPhrase();
    }
    
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.warn("Een fout is opgetreden bij print jackson: {}", e.toString());
            return "";
        }
    }    
}
