package app.bank.register.packages.controllers;

import app.bank.register.packages.entities.ClientDatesEntity;
import app.bank.register.packages.services.ClientDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/clientdates")
public class ClientDatesController {
    @Autowired
    private ClientDatesService clientDatesService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDatesEntity> getClientDates(@PathVariable Long id) {
        ClientDatesEntity dates = clientDatesService.getByClientId(id);
        return ResponseEntity.ok(dates);
    }

    @PostMapping("/")
    public ResponseEntity<ClientDatesEntity> createDates(@RequestBody ClientDatesEntity clientDates) {
        ClientDatesEntity dates = clientDatesService.saveDates(clientDates);
        return ResponseEntity.ok(dates);
    }

    @PutMapping("/")
    public ResponseEntity<ClientDatesEntity> updateDates(@RequestBody ClientDatesEntity clientDates) {
        ClientDatesEntity dates = clientDatesService.saveDates(clientDates);
        return ResponseEntity.ok(dates);
    }
}
