package app.bank.register.packages.repositories;

import app.bank.register.packages.entities.SavingCapacityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingCapacityRepository extends JpaRepository<SavingCapacityEntity, Long> {
    public SavingCapacityEntity findByClientId(Long clientId);
}
