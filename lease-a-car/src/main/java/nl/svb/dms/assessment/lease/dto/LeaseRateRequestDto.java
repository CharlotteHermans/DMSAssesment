package nl.svb.dms.assessment.lease.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaseRateRequestDto {
    @JsonProperty
    @NotNull(message = "Make can not be null")
    @Schema(description = "Make of the car", example="Lexus")
    String make;
    @JsonProperty
    @NotNull(message = "Model can not be null")
    @Schema(description = "Model of the car", example="IS250")
    String model;
    @JsonProperty
    @NotNull(message = "Version can not be null")
    @Schema(description = "Version of the model", example="Sport")
    String version;
    @JsonProperty
    @Positive(message = "Duration can not be null")
    @Min(12)
    @Max(60)
    @Schema(description = "Duration of the lease contract in months", example="24")
    Integer duration;
    @JsonProperty
    @Positive(message = "Mileage can not be null")
    @Min(10000)
    @Schema(description = "Mileage (per year) of the lease contract", example="10000")
    Integer mileage;
}
