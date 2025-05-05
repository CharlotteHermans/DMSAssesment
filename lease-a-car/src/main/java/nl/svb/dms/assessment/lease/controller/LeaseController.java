package nl.svb.dms.assessment.lease.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.svb.dms.assessment.lease.dto.LeaseRateRequestDto;
import nl.svb.dms.assessment.lease.dto.LeaseRateResponseDto;
import nl.svb.dms.assessment.lease.exception.ApiErrorResponse;
import nl.svb.dms.assessment.lease.service.CarService;
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
@Tag(name = "lease-controller")
public class LeaseController {
    private final CarService carService;

    @GetMapping(value = "/v1/cars",produces = "application/json")
    @Operation(summary="Get car info for the given carId.",
            description="Get car info for leasing a car" ,
            parameters = {@Parameter(in = ParameterIn.HEADER, description = "Correlation ID", name = "CID", schema = @Schema(type="string"))})
    @ApiResponse(responseCode = "200", description = "de aanvraag is succesvol verwerkt", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LeaseRateResponseDto.class))})
    @ApiResponse(responseCode = "401", description = "token ontbreekt",  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))})
    @ApiResponse(responseCode = "404", description = "geen data is gevonden",  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))})
    @ApiResponse(responseCode = "500", description = "onverwachte fout opgetreden",  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))})
    public ResponseEntity<LeaseRateResponseDto> getLeaseRate(@ParameterObject @Validated() final LeaseRateRequestDto leaseRateRequest){
        log.info("Call of determineLeaseRate");
        LeaseRateResponseDto leaseRateResponse = carService.determineLeaseRate(leaseRateRequest);

        log.info("result determineLeaseRate - HttpStatus: {}",  HttpStatus.OK);
        return ResponseEntity.ok(leaseRateResponse);
    }

}
