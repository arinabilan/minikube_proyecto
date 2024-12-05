package app.bank.register.packages.repositories;

import app.bank.register.packages.entities.ClientDatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDatesRepository extends JpaRepository<ClientDatesEntity, Long> {
    public ClientDatesEntity findByClientId(Long clientId);
}
