package app.bank.tracking.packages.services;

import app.bank.tracking.packages.entities.LoanEntity;
import app.bank.tracking.packages.model.SolicitudeEntity;
import app.bank.tracking.packages.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    RestTemplate restTemplate;

    public LoanEntity getLoanBySolicitudeId(Long solicitudeId) {
        return loanRepository.findBySolicitudeId(solicitudeId);
    }

    public SolicitudeEntity getSolicitude(Long id){
        SolicitudeEntity solicitude = restTemplate.getForObject("http://solicitude/solicitude/" + id, SolicitudeEntity.class);
        return solicitude;
    }

    public List<SolicitudeEntity> getAllSolicitudesByClientId(Long clientId){
        List<SolicitudeEntity> allSolicitudes = restTemplate.getForObject("http://solicitude/solicitude/" + clientId + "/client", List.class);
        return allSolicitudes;
    }

    public SolicitudeEntity changedSolicitudeState(Long solicitudeId){
        SolicitudeEntity solicitudeChanged = restTemplate.getForObject("http://solicitude/solicitude//change/solicitude/client/by/solicitude/id/client/choose/aprove/loan/by/id/solicitude/" + solicitudeId, SolicitudeEntity.class);
        return solicitudeChanged;
    }

    public double simulateAmount(double amount, double interestRate, double years) {

        double interesRate = interestRate * 100;
        double r = interesRate/12/100;
        double n = years;
        double pow = Math.pow((1+r), n);
        return Math.round(amount * ((r*pow)/(pow-1)));
    }

    public LoanEntity createLoan(Long solicitudeId){
        LoanEntity loan = new LoanEntity();
        SolicitudeEntity clientSolicitude = getSolicitude(solicitudeId);
        double amount = clientSolicitude.getAmount();
        double interestRate = clientSolicitude.getInterestRate();
        double month = clientSolicitude.getDeadline();
        double couta = simulateAmount(amount, interestRate, month);
        loan.setMonthlyFee(couta);
        double comision = amount * 0.01;
        loan.setComision(comision);
        double insuranceFee = amount * 0.0003;
        loan.setInsuranceFee(insuranceFee);
        double monthlyCost = couta + insuranceFee + 20000;
        loan.setMonthlyCost(monthlyCost);
        double totalCost = monthlyCost * month + comision;
        loan.setTotalCost(totalCost);
        loan.setSolicitudeId(solicitudeId);
        return loanRepository.save(loan);
    }

    public LoanEntity createLoan1(Long solicitudeId) {
        LoanEntity loan = new LoanEntity();
        SolicitudeEntity clientSolicitude = getSolicitude(solicitudeId);
        float amount = clientSolicitude.getAmount();
        double interestRate = clientSolicitude.getInterestRate();
        double month = clientSolicitude.getDeadline();
        double couta = simulateAmount(amount, interestRate, month);
        double comision = amount * 0.01;
        double insuranceFee = amount * 0.0003;
        double monthlyCost = couta + insuranceFee + 20000;
        double totalCost = monthlyCost * month + comision;
        loan.setMonthlyFee(couta);
        loan.setComision(comision);
        loan.setInsuranceFee(insuranceFee);
        loan.setMonthlyCost(monthlyCost);
        loan.setTotalCost(totalCost);
        loan.setSolicitudeId(solicitudeId);
        return loanRepository.save(loan);
    }

}
