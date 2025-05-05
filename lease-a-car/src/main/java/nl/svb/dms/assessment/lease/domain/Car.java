package nl.svb.dms.assessment.lease.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private String make;
    private String model;
    private String version;
    private BigDecimal nettPrice;
}
