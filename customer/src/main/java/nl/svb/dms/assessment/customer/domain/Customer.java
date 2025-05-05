package nl.svb.dms.assessment.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {
    private String name;
    private String make;
    private String model;
    private String version;
    private int duration;
    private int mileage;
    private BigDecimal leaseRate;
}
