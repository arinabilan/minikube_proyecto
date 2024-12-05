package app.bank.solicitude.packages.services;

import app.bank.solicitude.packages.entities.DocumentEntity;
import app.bank.solicitude.packages.entities.LoanRequirementEntity;
import app.bank.solicitude.packages.repositories.LoanRequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanRequirementService {
    @Autowired
    private LoanRequirementRepository loanRequirementRepository;

    public ArrayList<LoanRequirementEntity> getAllLoanRequirements() {
        return (ArrayList<LoanRequirementEntity>) loanRequirementRepository.findAll();
    }

    public LoanRequirementEntity findByTypeLoanId(Long typeId) {
        return loanRequirementRepository.findByTypeLoanId(typeId);
    }

    public Integer findMaxMonthsByTypeLoanId(Long id){
        return loanRequirementRepository.findMaxMonthsByTypeLoanId(id);
    }

    public BigDecimal findInterestRateByTypeLoanId(Long id){
        return loanRequirementRepository.findInterestRateByTypeLoanId(id);
    }

    public BigDecimal findMaxAmountByTypeLoanId(Long id){
        return loanRequirementRepository.findMaxAmountByTypeLoanId(id);
    }

    public List<DocumentEntity> findDocumentsByTypeLoanId(Long typeLoanId){
        return loanRequirementRepository.findDocumentsByTypeLoanId(typeLoanId);
    }

    public List<Long> findDocumentsByTypeLoanId1(Long typeLoanId){
        List<DocumentEntity> documentByLoan = loanRequirementRepository.findDocumentsByTypeLoanId(typeLoanId);
        List<Long> documentIdsByLoan = documentByLoan.stream()
                .map(DocumentEntity::getId)
                .collect(Collectors.toList());
        return documentIdsByLoan;
    }

    public LoanRequirementEntity updateLoanRequirement(LoanRequirementEntity loanRequirement){
        return loanRequirementRepository.save(loanRequirement);
    }

    public double percentRate(double interestRate){
        return interestRate * 100;
    }

    public double year(double month){
        return Math.floor(month / 12);
    }
}
