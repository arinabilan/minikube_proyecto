package app.bank.loanEvaluation.packages.services;

import app.bank.loanEvaluation.packages.entities.ExecutiveEntity;
import app.bank.loanEvaluation.packages.model.*;
import app.bank.loanEvaluation.packages.repositories.ExecutiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExecutiveService {
    @Autowired
    private ExecutiveRepository executiveRepository;

    @Autowired
    RestTemplate restTemplate;


    public ExecutiveEntity getExecutive(Long id){
        Optional<ExecutiveEntity> client = executiveRepository.findById(id);
        return client.orElse(null); // Uso seguro de Optional
    }

    public ExecutiveEntity getByEmailAndPassword(String email, String password){
        Optional<ExecutiveEntity> optionalExecutive = executiveRepository.findByEmail(email);

        if(optionalExecutive.isPresent()){
            ExecutiveEntity executive = optionalExecutive.get();

            if(executive.getPassword().equals(password)){
                return executive;
            } else {
                throw new IllegalArgumentException("Constraseña incorrecta");
            }
        } else {
            throw new IllegalArgumentException("Email no encontrado");
        }
    }

    public ExecutiveEntity addExecutive(ExecutiveEntity executive){
        return executiveRepository.save(executive);
    }

    public ExecutiveEntity updateExecutive(ExecutiveEntity executive){
        return executiveRepository.save(executive);
    }

    public Boolean deleteExecutive(Long id) throws Exception{
        try{
            executiveRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public SolicitudeEntity getSolicitude(Long id){
        SolicitudeEntity solicitude = restTemplate.getForObject("http://solicitude/solicitude/" + id, SolicitudeEntity.class);
        return solicitude;
    }

    public List<ClientDocumentEntity> getDocumentsByClientId(Long clientId){
        List<ClientDocumentEntity> documents = restTemplate.getForObject("http://solicitude/solicitude/find/list/document/by/client/id/" + clientId, List.class);
        return documents;
    }

    public List<Long> getDocumentsByClientId1(Long clientId){
        List<Long> documentIds = restTemplate.getForObject("http://solicitude/solicitude/get/document/ids/by/client/id/listdocument/" + clientId, List.class);
        return documentIds;
    }

    public ArrayList<DocumentEntity> getMinimumRequiriment(Boolean bool){
        ArrayList<DocumentEntity> documents = restTemplate.getForObject("http://solicitude/solicitude/find/document/byMinimun/" + bool, ArrayList.class);
        return documents;
    }

    public List<Long> getMinimumRequiriment1(Boolean bool){
        List<Long> documentsIds = restTemplate.getForObject("http://solicitude/solicitude/find/document/by/byMinimun/minimum/" + bool, List.class);
        return documentsIds;
    }

    public List<DocumentEntity> getDocumentsByTypeloanId(Long id){
        List<DocumentEntity> documents = restTemplate.getForObject("http://solicitude/solicitude/find/loan/requirement/by/type/loan/id/list/documents/list/loan/" + id, List.class);
        return documents;
    }

    public List<Long> getDocumentsByTypeloanId1(Long id){
        List<Long> documents = restTemplate.getForObject("http://solicitude/solicitude/find/loan/requirement/by/type/loan/id/documents/list/loan/by/id/" + id, List.class);
        return documents;
    }

    public SolicitudeEntity saveSolicitudeEvaluated(SolicitudeEntity solicitudeEvaluated) {
        String url = "http://solicitude/solicitude/";

        // Encapsular el objeto en un HttpEntity
        HttpEntity<SolicitudeEntity> requestEntity = new HttpEntity<>(solicitudeEvaluated);

        // Llamar al servicio con el método PUT (o el que desees)
        ResponseEntity<SolicitudeEntity> response = restTemplate.exchange(
                url,
                HttpMethod.PUT, // Cambia a HttpMethod.POST si corresponde
                requestEntity,
                SolicitudeEntity.class
        );

        // Retornar el cuerpo de la respuesta
        return response.getBody();
    }

    public ClientDatesEntity getClientDatesById(Long id){
        ClientDatesEntity dates = restTemplate.getForObject("http://register/register/clientdates/" + id, ClientDatesEntity.class);
        return dates;
    }

    public double simulateAmount(double amount, double interesRate, double years ) {
        double r = interesRate/12/100;
        double n = years * 12;
        double pow = Math.pow((1+r), n);
        return Math.round(amount * ((r*pow)/(pow-1)));
    }

    public ClientEntity getClientById(Long id){
        ClientEntity client = restTemplate.getForObject("http://register/register/" + id, ClientEntity.class);
        return client;
    }

    public LoanEntity createLoan(Long id){
        LoanEntity loan = restTemplate.getForObject("http://tracking/tracking/createLoan/dd/dd/" + id, LoanEntity.class);
        return loan;
    }

    public SolicitudeEntity EvaluateSolicitude(Long id) {
        SolicitudeEntity solicitude = getSolicitude(id);
        // si la solicitud ya pasó de En evaluación
        if (solicitude.getState() > 3) {
            return solicitude;
        }
        Long clientId = solicitude.getClientId();
        Long loanId = solicitude.getLoanType().getId(); //agregue esta linea
        List<Long> documentsClient =  getDocumentsByClientId1(clientId);
        //List<Long> documentIds = documentsClient.stream()
                //.map(clientDocument -> clientDocument.getDocument().getId())
                //.collect(Collectors.toList());

        // 1 - Verifica si la documentación mínima requerida + la documentación del tipo de prestamo está completa
        List<Long> documentsMinimun = getMinimumRequiriment1(true);
        //List<Long> documentIds1 = documentsMinimun.stream()
                //.map(DocumentEntity::getId)
                //.collect(Collectors.toList());
        boolean documentsMinimunRequired = documentsClient.isEmpty() && documentsMinimun.isEmpty() || documentsClient.containsAll(documentsMinimun); //agregue All
        List<Long> documentByLoan = getDocumentsByTypeloanId1(loanId); // cambie de clientId a loanId
        //List<Long> documentIdsByLoan = documentByLoan.stream()
                //.map(DocumentEntity::getId)
                //.collect(Collectors.toList());
        boolean documentsLLoanRequired = documentsClient.isEmpty() && documentByLoan.isEmpty() || documentsClient.containsAll(documentByLoan); //agregue All
        if (!documentsMinimunRequired || !documentsLLoanRequired) {
            solicitude.setState(2);
            return saveSolicitudeEvaluated(solicitude);
        }

        ClientDatesEntity clientDates = getClientDatesById(clientId);
        // 2(R1) - Verifica que la relación cuota/ingreso no sea mayor a 35%
        double monthCuote = simulateAmount(solicitude.getAmount(), solicitude.getInterestRate() * 100, (double) (solicitude.getDeadline() / 12));
        int monthSalary = clientDates.getMonthSalary();
        double relationCuoteSalary = (monthCuote / monthSalary) * 100;
        if (relationCuoteSalary > 35) {
            solicitude.setState(7); // rechazada
            return saveSolicitudeEvaluated(solicitude);
        }

        // 3(R2) - Verifica si tiene buen historial crediticio (DICOM)
        if (clientDates.getDicom()) {
            solicitude.setState(7); // rechazada
            return saveSolicitudeEvaluated(solicitude);
        }

        // 4(R3) - Verificación de estado contractual
        if (!((clientDates.getType() == 1 &&
                clientDates.getMediaSalary() > 750000 &&
                clientDates.getMonthSalary() - clientDates.getMonthlyDebt() > 500000)
                || (clientDates.getType() == 2 && clientDates.getInitialContract() > 1 ))) {
            solicitude.setState(7); // rechazada
            return saveSolicitudeEvaluated(solicitude);
        }

        // 5(R4) - Verificación deuda/ingreso
        double relationDebtSalary = ((monthCuote + clientDates.getMonthlyDebt()) / monthSalary) * 100;
        if (relationDebtSalary > 50) {
            solicitude.setState(7); // rechazada
            return saveSolicitudeEvaluated(solicitude);
        }

        // 6(R6) - Verificación edad
        int currentAge = Period.between(getClientById(clientId).getBirthday(), LocalDate.now()).getYears();
        if (currentAge + (solicitude.getDeadline() / 12) > 70) {
            solicitude.setState(7); // rechazada
            return saveSolicitudeEvaluated(solicitude);
        }

        solicitude.setState(4);// pre-aprobada
        createLoan(id);
        return saveSolicitudeEvaluated(solicitude);
    }
}
