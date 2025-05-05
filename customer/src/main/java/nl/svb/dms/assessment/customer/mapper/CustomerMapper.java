package nl.svb.dms.assessment.customer.mapper;

import jakarta.validation.constraints.NotNull;
import nl.svb.dms.assessment.customer.dao.CustomerDao;
import nl.svb.dms.assessment.customer.dao.LeaseContractDao;
import nl.svb.dms.assessment.customer.domain.Customer;
import nl.svb.dms.assessment.customer.dto.CustomerResponseDto;
import nl.svb.dms.model.leaseacar.LacLeaseRateResponseDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomerMapper {

    public Customer map (@NotNull CustomerDao customerDao, @NotNull LeaseContractDao leaseContractDao, LacLeaseRateResponseDto leaseRateResponse) {
        return Customer.builder()
                .name(customerDao.getName())
                .make(leaseContractDao.getMake())
                .model(leaseContractDao.getModel())
                .version(leaseContractDao.getVersion())
                .duration(leaseContractDao.getDuration())
                .mileage(leaseContractDao.getMilage())
                .leaseRate(leaseRateResponse != null ? leaseRateResponse.getLeaseRate() : null)
                .build();
    }
    public Customer map (@NotNull CustomerDao customerDao, @NotNull LeaseContractDao leaseContractDao) {
        return map(customerDao, leaseContractDao, null);
    }

    public CustomerResponseDto map (@NotNull Customer customer, @NotNull BigDecimal leaseRate) {
        return CustomerResponseDto.builder()
                .make(customer.getMake())
                .model(customer.getModel())
                .version(customer.getVersion())
                .duration(customer.getDuration())
                .mileage(customer.getMileage())
                .leaseRate(leaseRate)
                .build();
    }
}
