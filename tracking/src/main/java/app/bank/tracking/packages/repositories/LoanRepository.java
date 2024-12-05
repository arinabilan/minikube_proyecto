package app.bank.tracking.packages.repositories;

import app.bank.tracking.packages.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    public LoanEntity findBySolicitudeId(Long solicitudeId);
}
