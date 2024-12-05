package app.bank.loanEvaluation.packages.controllers;

import app.bank.loanEvaluation.packages.entities.ExecutiveEntity;
import app.bank.loanEvaluation.packages.model.*;
import app.bank.loanEvaluation.packages.services.ExecutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loanEvaluation")
public class ExecutiveController {
    @Autowired
    private ExecutiveService executiveService;

    @GetMapping("/{id}")
    public ResponseEntity<ExecutiveEntity> getExecutive(@PathVariable Long id) {
        ExecutiveEntity executive = executiveService.getExecutive(id);
        return ResponseEntity.ok(executive);
    }

    @PostMapping("/login/email")
    public ResponseEntity<Object> findByEmailAndPassword(@RequestBody Map<String, String> credentials){
        String email = credentials.get("email");
        String password = credentials.get("password");
        try{
            ExecutiveEntity executive = executiveService.getByEmailAndPassword(email, password);
            return ResponseEntity.ok(executive);
        } catch (IllegalArgumentException e) {
            // Si hay algún problema con la autenticación, retorna un error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<ExecutiveEntity> createExecutive(@RequestBody ExecutiveEntity executive) {
        ExecutiveEntity newExecutive = executiveService.addExecutive(executive);
        return ResponseEntity.ok(newExecutive);
    }

    @PutMapping("/")
    public ResponseEntity<ExecutiveEntity> updateExecutive(@RequestBody ExecutiveEntity executive) {
        ExecutiveEntity updatedExecutive = executiveService.updateExecutive(executive);
        return ResponseEntity.ok(updatedExecutive);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteExecutive(@PathVariable Long id) throws Exception{
        executiveService.deleteExecutive(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getsolicitude/{id}")
    public ResponseEntity<SolicitudeEntity> getSolicitudeById(@PathVariable Long id){
        SolicitudeEntity solicitude = executiveService.getSolicitude(id);
        return ResponseEntity.ok(solicitude);
    }

    @GetMapping("/{clientId}/getdocuments")
    public ResponseEntity<List<ClientDocumentEntity>> getAllClientDocuments(@PathVariable Long clientId){
        List<ClientDocumentEntity> documents = executiveService.getDocumentsByClientId(clientId);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/getdocument/requirement/{bool}")
    public ResponseEntity<ArrayList<DocumentEntity>> getDocumentsByRequirement(@PathVariable Boolean bool){
        ArrayList<DocumentEntity> documents = executiveService.getMinimumRequiriment(bool);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/getdocuments/type/loan/{id}")
    public ResponseEntity<List<DocumentEntity>> getDocumentsByLoanId(@PathVariable Long id){
        List<DocumentEntity> documents = executiveService.getDocumentsByTypeloanId(id);
        return ResponseEntity.ok(documents);
    }

    @PutMapping("/solicitude/evaluated")
    public ResponseEntity<SolicitudeEntity> saveEvaluatedSolicitude(@RequestBody SolicitudeEntity solicitude){
        SolicitudeEntity solicitudeEvaluated = executiveService.saveSolicitudeEvaluated(solicitude);
        return ResponseEntity.ok(solicitudeEvaluated);
    }

    @GetMapping("/get/client/dates/by/{id}")
    public ResponseEntity<ClientDatesEntity> getClientDatesById(@PathVariable Long id){
        ClientDatesEntity clientDates = executiveService.getClientDatesById(id);
        return ResponseEntity.ok(clientDates);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudeEntity> updateSolicitude(@PathVariable Long id, @RequestBody SolicitudeEntity solicitude){
        SolicitudeEntity solicitudeUpdates = executiveService.EvaluateSolicitude(id);
        return ResponseEntity.ok(solicitudeUpdates);
    }

    @GetMapping("/get/client/by/client/id/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable Long id){
        ClientEntity clientEntity = executiveService.getClientById(id);
        return ResponseEntity.ok(clientEntity);
    }

    @GetMapping("/evaluate/solicitude/by/solicitude/id/solicitude/{id}")
    public ResponseEntity<SolicitudeEntity> evaluate(@PathVariable("id") Long id) {
        SolicitudeEntity solicitudes = executiveService.EvaluateSolicitude(id);
        return ResponseEntity.ok(solicitudes);
    }
}
