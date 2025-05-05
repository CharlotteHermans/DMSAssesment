package nl.svb.dms.assessment.customer.service;

import nl.svb.dms.assessment.customer.dto.CustomerRequestDto;
import nl.svb.dms.assessment.customer.dto.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto retrieveCustomerInfo(CustomerRequestDto leasePriceRequest);
}
