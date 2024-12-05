package app.bank.solicitude.packages.services;

import app.bank.solicitude.packages.entities.SolicitudeEntity;
import app.bank.solicitude.packages.repositories.ClientDocumentRepository;
import app.bank.solicitude.packages.repositories.DocumentRepository;
import app.bank.solicitude.packages.repositories.LoanRequirementRepository;
import app.bank.solicitude.packages.repositories.SolicitudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitudeService {
    @Autowired
    private SolicitudeRepository solicitudeRepository;
    @Autowired
    private ClientDocumentRepository clientDocumentRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private LoanRequirementRepository loanRequirementRepository;

    public ArrayList<SolicitudeEntity> getAllSolicitudes() {
        return (ArrayList<SolicitudeEntity>) solicitudeRepository.findAll();
    }

    public List<SolicitudeEntity> getAllSolicitudesByClientId(Long clientId) {
        return solicitudeRepository.findByClientId(clientId);
    }

    public SolicitudeEntity saveSolicitude(SolicitudeEntity solicitude) {
        float amount = solicitude.getAmount();
        float maxAmount = solicitude.getMaxAmount();
        float totalAmount = calculateAmount(amount, maxAmount);
        int years = solicitude.getDeadline();
        int months = calculateMonths(years);
        solicitude.setCalculatedAmount(totalAmount);
        solicitude.setDeadline(months);
        return solicitudeRepository.save(solicitude);
    }

    float calculateAmount(float amount, float maxAmount){
        return amount * maxAmount;
    }

    int calculateMonths(int years){
        return years * 12;
    }

    public SolicitudeEntity updateSolicitude(Long id, Long executiveId, int state) {
        SolicitudeEntity updated = solicitudeRepository.findById(id).get();
        if (state == 1) { // En Revisión Inicial
            updated.setExecutiveId(executiveId);
        }
        updated.setState(state);
        return solicitudeRepository.save(updated);
    }

    public SolicitudeEntity saveSolicitudeEvaluated(SolicitudeEntity solicitude) {
        return solicitudeRepository.save(solicitude);
    }

    public SolicitudeEntity getById(Long id) {
        return solicitudeRepository.findById(id).get();
    }

    public SolicitudeEntity changeState(Long id){
        SolicitudeEntity clientSolicitude = getById(id);
        clientSolicitude.setState(6);
        return solicitudeRepository.save(clientSolicitude);
    }
}
