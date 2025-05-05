package nl.svb.dms.assessment.customer.repository;

import nl.svb.dms.assessment.customer.dao.LeaseContractDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaseContractRepository extends JpaRepository<LeaseContractDao, Integer> {
    public Optional<LeaseContractDao> findByCustomerId(Integer customerId);
}
