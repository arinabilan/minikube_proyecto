package app.bank.loanEvaluation.packages.repositories;

import app.bank.loanEvaluation.packages.entities.ExecutiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExecutiveRepository extends JpaRepository<ExecutiveEntity, Long> {
    public Optional<ExecutiveEntity> findByEmail(String email);
}
