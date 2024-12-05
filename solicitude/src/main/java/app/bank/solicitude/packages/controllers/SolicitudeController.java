package app.bank.solicitude.packages.controllers;

import app.bank.solicitude.packages.entities.SolicitudeEntity;
import app.bank.solicitude.packages.services.SolicitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/solicitude")
public class SolicitudeController {
    @Autowired
    private SolicitudeService solicitudeService;

    @GetMapping("/")
    public ResponseEntity<List<SolicitudeEntity>> findAll() {
        List<SolicitudeEntity> solicitudes = solicitudeService.getAllSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    @PostMapping("/")
    public ResponseEntity<SolicitudeEntity> saveSolicitude(@RequestBody SolicitudeEntity solicitude) {
        SolicitudeEntity saved = solicitudeService.saveSolicitude(solicitude);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/{state}")
    public ResponseEntity<SolicitudeEntity> updateSolicitude(@PathVariable("id") Long id, @PathVariable("state") int state, @RequestParam Long executiveId) {
        SolicitudeEntity updated = solicitudeService.updateSolicitude(id, executiveId, state);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/")
    public ResponseEntity<SolicitudeEntity> saveEvaluated(@RequestBody SolicitudeEntity solicitude) {
        SolicitudeEntity evaluated = solicitudeService.saveSolicitudeEvaluated(solicitude);
        return ResponseEntity.ok(evaluated);
    }

    @GetMapping("/{id}/client")
    public ResponseEntity<List<SolicitudeEntity>> solicitudeByClient(@PathVariable("id") Long id) {
        List<SolicitudeEntity> solicitudes = solicitudeService.getAllSolicitudesByClientId(id);
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudeEntity> solicitudeById(@PathVariable("id") Long id) {
        SolicitudeEntity solicitude = solicitudeService.getById(id);
        return ResponseEntity.ok(solicitude);
    }
}
