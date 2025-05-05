package nl.svb.dms.assessment.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDto {
    @JsonProperty
    @NotNull(message = "Name can not be null")
    @Schema(description = "Name of the customer", example="Phil Collins")
    String name;
}
