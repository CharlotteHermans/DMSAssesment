package nl.svb.dms.assessment.lease.repository;

import nl.svb.dms.assessment.lease.dao.CarDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarDao, Integer> {
    public Optional<CarDao> findByMakeAndModelAndVersion(String make, String model, String version);
}
