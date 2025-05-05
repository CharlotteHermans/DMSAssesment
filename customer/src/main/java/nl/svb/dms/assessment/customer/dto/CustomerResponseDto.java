package nl.svb.dms.assessment.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {
    @JsonProperty
    @Schema(description = "Make of the car", example="Lexus")
    String make;
    @JsonProperty
    @Schema(description = "Model of the car", example="IS250")
    String model;
    @JsonProperty
    @Schema(description = "Version of the model", example="Sport")
    String version;
    @JsonProperty
    @Schema(description = "Duration of the lease contract in months", example="24")
    Integer duration;
    @JsonProperty
    @Schema(description = "Mileage (per year) of the lease contract", example="10000")
    Integer mileage;
    @JsonProperty
    @Schema(description = "leaseRate per month")
    private BigDecimal leaseRate;
}
