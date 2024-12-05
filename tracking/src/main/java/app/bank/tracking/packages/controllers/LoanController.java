package app.bank.tracking.packages.controllers;

import app.bank.tracking.packages.entities.LoanEntity;
import app.bank.tracking.packages.model.SolicitudeEntity;
import app.bank.tracking.packages.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping("/{solicitudeId}")
    public ResponseEntity<LoanEntity> getLoanBySolicitudeId(@PathVariable Long solicitudeId) {
        LoanEntity loan = loanService.getLoanBySolicitudeId(solicitudeId);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/getsolicitudes/{clientId}")
    public ResponseEntity<List<SolicitudeEntity>> getAllSolicitudesByClientId(@PathVariable Long clientId) {
        List<SolicitudeEntity> allSolicitudes = loanService.getAllSolicitudesByClientId(clientId);
        return ResponseEntity.ok(allSolicitudes);
    }

    @GetMapping("/getsolicitude/byid/{solicitudeId}")
    public ResponseEntity<SolicitudeEntity> getSolicitudeBySolicitudeId(@PathVariable Long solicitudeId) {
        SolicitudeEntity solicitude = loanService.getSolicitude(solicitudeId);
        return ResponseEntity.ok(solicitude);
    }

    @GetMapping("/createLoan/dd/dd/{solicitudeId}")
    public ResponseEntity<LoanEntity> createLoan(@PathVariable Long solicitudeId) {
        LoanEntity loan = loanService.createLoan1(solicitudeId);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/change/solicitude/by/solicitudeId/{solicitudeId}")
    public ResponseEntity<SolicitudeEntity> changeSolicitudeState(@PathVariable Long solicitudeId) {
        SolicitudeEntity solicitudeChanged = loanService.changedSolicitudeState(solicitudeId);
        return ResponseEntity.ok(solicitudeChanged);
    }
}
