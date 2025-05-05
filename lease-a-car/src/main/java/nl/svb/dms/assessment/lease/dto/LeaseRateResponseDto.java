package nl.svb.dms.assessment.lease.dto;

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
public class LeaseRateResponseDto {
    @JsonProperty
    @Schema(description = "leaseRate per month")
    private BigDecimal leaseRate;
}
