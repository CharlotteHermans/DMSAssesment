package nl.svb.dms.assessment.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.svb.dms.assessment.customer.dto.CustomerResponseDto;
import nl.svb.dms.assessment.customer.dto.CustomerRequestDto;
import nl.svb.dms.assessment.customer.exception.ApiErrorResponse;
import nl.svb.dms.assessment.customer.service.CustomerService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "customer-controller")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(value = "/v1/customers",produces = "application/json")
    @Operation(summary="Get customer info.",
            description="Get customer info" ,
            parameters = {@Parameter(in = ParameterIn.HEADER, description = "Correlation ID", name = "CID", schema = @Schema(type="string"))})
    @ApiResponse(responseCode = "200", description = "de aanvraag is succesvol verwerkt", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponseDto.class))})
    @ApiResponse(responseCode = "401", description = "token ontbreekt",  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))})
    @ApiResponse(responseCode = "404", description = "geen data is gevonden",  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))})
    @ApiResponse(responseCode = "500", description = "onverwachte fout opgetreden",  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))})
    public ResponseEntity<CustomerResponseDto> getCustomerInfo(@ParameterObject @Validated() final CustomerRequestDto customerRequest){
        log.info("Call of getLeaseRate");
        CustomerResponseDto CustomerResponse = customerService.retrieveCustomerInfo(customerRequest);

        log.info("result getLeaseRate - HttpStatus: {}",  HttpStatus.OK);
        return ResponseEntity.ok(CustomerResponse);
    }

}
