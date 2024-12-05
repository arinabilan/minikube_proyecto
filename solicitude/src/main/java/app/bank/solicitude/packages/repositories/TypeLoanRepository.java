package app.bank.solicitude.packages.repositories;

import app.bank.solicitude.packages.entities.TypeLoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeLoanRepository extends JpaRepository<TypeLoanEntity, Long> {
    public TypeLoanEntity getLoanByType(String type);
}
