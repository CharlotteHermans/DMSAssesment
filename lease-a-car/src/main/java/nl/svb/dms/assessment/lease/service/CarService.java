package nl.svb.dms.assessment.lease.service;

import nl.svb.dms.assessment.lease.dto.LeaseRateRequestDto;
import nl.svb.dms.assessment.lease.dto.LeaseRateResponseDto;

public interface CarService {
    LeaseRateResponseDto determineLeaseRate(LeaseRateRequestDto leasePriceRequest);
}
