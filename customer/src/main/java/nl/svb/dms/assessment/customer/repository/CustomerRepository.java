package nl.svb.dms.assessment.customer.repository;

import nl.svb.dms.assessment.customer.dao.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDao, Integer> {
    public Optional<CustomerDao> findByName(String name);
}
