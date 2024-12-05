package app.bank.solicitude.packages.services;

import app.bank.solicitude.packages.entities.TypeLoanEntity;
import app.bank.solicitude.packages.repositories.TypeLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TypeLoanService {
    @Autowired
    private TypeLoanRepository typeLoanRepository;

    public ArrayList<TypeLoanEntity> getAllTypeLoans() {
        return (ArrayList<TypeLoanEntity>) typeLoanRepository.findAll();
    }

    public TypeLoanEntity getTypeLoan(String type) {
        return typeLoanRepository.getLoanByType(type);
    }

    public TypeLoanEntity createTypeLoan(TypeLoanEntity typeLoan) {
        return typeLoanRepository.save(typeLoan);
    }
}
