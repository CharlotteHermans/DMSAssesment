package nl.svb.dms.assessment.lease.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import nl.svb.dms.assessment.lease.dao.CarDao;
import nl.svb.dms.assessment.lease.domain.Car;
import nl.svb.dms.assessment.lease.dto.LeaseRateRequestDto;
import nl.svb.dms.assessment.lease.dto.LeaseRateResponseDto;
import nl.svb.dms.assessment.lease.mapper.CarMapper;
import nl.svb.dms.assessment.lease.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarServiseImpl implements CarService{

    private final CarRepository carRepo;
    private final CarMapper carMapper;

    private static final Long INTEREST_RATE = 4L;
    /**
     * Determine the lease price of the requested car
     * @param leasePriceRequest {@link LeaseRateRequestDto} request info
     */
    @Override
    public LeaseRateResponseDto determineLeaseRate(LeaseRateRequestDto leasePriceRequest) {

        Car car = findCar(leasePriceRequest.getMake(), leasePriceRequest.getModel(), leasePriceRequest.getVersion());
        BigDecimal leaserate = calculateLeaseRate(car, leasePriceRequest.getDuration(), leasePriceRequest.getMileage());
        return LeaseRateResponseDto.builder()
                .leaseRate(leaserate)
                .build();
    }

    /**
     * Retrieve car info from database
     * @param make {@link String} make of the car to retrieve
     * @param model {@link String} model of the car to retrieve
     * @param version {@link String} version of the model
     * @return {@link Car} car
     */
    protected Car findCar(String make, String model, String version) {
        Optional<CarDao> carDao = carRepo.findByMakeAndModelAndVersion(make, model, version);
        if(carDao.isEmpty()) {
            throw new EntityNotFoundException("No car found");
        }
        return carMapper.map(carDao.get());
    }

    /**
     * Calculate leaserate:
     *  ((( mileage / 12 ) * duration ) / Nett price) + ((( Interest rate / 100 ) * Nett price) / 12 )
     * @param car {@link Car} car to lease
     * @param duration {@link Integer} duration of the leasecontract in months
     * @param mileage {@link Integer} mileage of the leasecontract per year
     * @return calculated leaserate per month
     */
    protected BigDecimal calculateLeaseRate(Car car, Integer duration, Integer mileage) {
        // berekening  ((( mileage / 12 ) * duration ) / Nett price) + ((( Interest rate / 100 ) * Nett price) / 12 )
        float leaserate = (((mileage / 12) * duration) / car.getNettPrice().floatValue()) + (((INTEREST_RATE / 100) * car.getNettPrice().floatValue()) / 12);
        return new BigDecimal(Float.toString(leaserate));
    }
}
