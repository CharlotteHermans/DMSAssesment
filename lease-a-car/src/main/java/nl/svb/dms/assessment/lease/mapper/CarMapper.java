package nl.svb.dms.assessment.lease.mapper;

import jakarta.validation.constraints.NotNull;
import nl.svb.dms.assessment.lease.dao.CarDao;
import nl.svb.dms.assessment.lease.domain.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car map(@NotNull CarDao carDao) {
        return Car.builder()
                .make(carDao.getMake())
                .model(carDao.getModel())
                .version(carDao.getVersion())
                .nettPrice(carDao.getNettPrice())
                .build();
    }


}
