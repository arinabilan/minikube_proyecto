package app.bank.register.packages.repositories;

import app.bank.register.packages.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public Optional<ClientEntity> findByEmail(String email);
}
