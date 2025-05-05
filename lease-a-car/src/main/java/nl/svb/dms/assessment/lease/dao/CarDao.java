package nl.svb.dms.assessment.lease.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CAR",indexes = @Index(columnList = "make,model,version", unique = true))
public class CarDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private Integer doors;

    @Column(nullable = false)
    private BigDecimal grossPrice;

    @Column(nullable = false)
    private BigDecimal nettPrice;

    @Column(nullable = false)
    private Integer hp;
}
