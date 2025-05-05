package nl.svb.dms.assessment.customer.service;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.assessment.customer.adapter.LeaseAdapter;
import nl.svb.dms.assessment.customer.dao.CustomerDao;
import nl.svb.dms.assessment.customer.dao.LeaseContractDao;
import nl.svb.dms.assessment.customer.domain.Customer;
import nl.svb.dms.assessment.customer.dto.CustomerRequestDto;
import nl.svb.dms.assessment.customer.dto.CustomerResponseDto;
import nl.svb.dms.assessment.customer.exception.CustomerException;
import nl.svb.dms.assessment.customer.mapper.CustomerMapper;
import nl.svb.dms.assessment.customer.repository.CustomerRepository;
import nl.svb.dms.assessment.customer.repository.LeaseContractRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepo;
    private final LeaseContractRepository leaseContractRepo;
    private final LeaseAdapter leaseAdapter;
    private final CustomerMapper mapper;

    public CustomerResponseDto retrieveCustomerInfo(CustomerRequestDto customerRequest) {
        BigDecimal leaseRate = null;
        Optional<CustomerDao> customerDao = customerRepo.findByName(customerRequest.getName());
        if (customerDao.isEmpty()) {
            throw new CustomerException("No customer");
        }
        Optional<LeaseContractDao> contract = leaseContractRepo.findByCustomerId(customerDao.get().getId());
        if(contract.isEmpty()) {
            throw new CustomerException("No lease contract");
        }
        Customer mappedCustomer = mapper.map(customerDao.get(), contract.get());
        leaseRate = leaseAdapter.retrieveLeaseRate(mappedCustomer);

        return mapper.map(mappedCustomer, leaseRate);

    }
}
