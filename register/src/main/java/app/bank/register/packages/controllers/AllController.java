package app.bank.register.packages.controllers;

import app.bank.register.packages.entities.ClientDatesEntity;
import app.bank.register.packages.entities.ClientEntity;
import app.bank.register.packages.entities.SavingCapacityEntity;
import app.bank.register.packages.services.ClientDatesService;
import app.bank.register.packages.services.ClientService;
import app.bank.register.packages.services.FileUploadService;
import app.bank.register.packages.services.SavingCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class AllController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientDatesService clientDatesService;

    @Autowired
    SavingCapacityService savingCapacityService;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/")
    public ResponseEntity<List<ClientEntity>> listClients(){
        List<ClientEntity> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getById(@PathVariable Long id){
        ClientEntity client = clientService.getById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/clientdates/{id}")
    public ResponseEntity<ClientDatesEntity> getClientDates(@PathVariable Long id) {
        ClientDatesEntity dates = clientDatesService.getByClientId(id);
        return ResponseEntity.ok(dates);
    }

    @GetMapping("/get/capacity/{id}")
    public ResponseEntity<SavingCapacityEntity> getCapacityByClientId(@PathVariable Long id) {
        SavingCapacityEntity savingCapacity = savingCapacityService.getByClientId(id);
        return ResponseEntity.ok(savingCapacity);
    }

    @GetMapping("/down/load/file/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Resource resource = fileUploadService.loadFileAsResource(fileName);
        String contentType = fileUploadService.getContentType(fileName);

        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }







    @PostMapping("/login/email")
    public ResponseEntity<Object> findByEmailAndPassword(@RequestBody Map<String, String> credentials){
        String email = credentials.get("email");
        String password = credentials.get("password");
        try{
            ClientEntity client = clientService.findByEmailAndPassword(email,password);
            return ResponseEntity.ok(client);
        } catch (IllegalArgumentException e) {
            // Si hay algún problema con la autenticación, retorna un error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<ClientEntity> saveClient(@RequestBody ClientEntity client){
        ClientEntity clientNew = clientService.saveClient(client);
        return ResponseEntity.ok(clientNew);
    }

    @PostMapping("/createdates")
    public ResponseEntity<ClientDatesEntity> createDates(@RequestBody ClientDatesEntity clientDates) {
        ClientDatesEntity dates = clientDatesService.saveDates(clientDates);
        return ResponseEntity.ok(dates);
    }

    @PostMapping("/create/saving/capacity")
    public ResponseEntity<SavingCapacityEntity> createSavingCapacity(@RequestBody SavingCapacityEntity saving) {
        SavingCapacityEntity savingCapacity = savingCapacityService.saveCapacity(saving);
        return ResponseEntity.ok(savingCapacity);
    }











    @PutMapping("/")
    public ResponseEntity<ClientEntity> updateClient(@RequestBody ClientEntity client){
        ClientEntity clientUpdated = clientService.updateClient(client);
        return ResponseEntity.ok(clientUpdated);
    }

    @PutMapping("/updatedates")
    public ResponseEntity<ClientDatesEntity> updateDates(@RequestBody ClientDatesEntity clientDates) {
        ClientDatesEntity dates = clientDatesService.saveDates(clientDates);
        return ResponseEntity.ok(dates);
    }

    @PutMapping("/update/saving")
    public ResponseEntity<SavingCapacityEntity> updateSavingCapacity(@RequestBody SavingCapacityEntity saving) {
        SavingCapacityEntity savingCapacity = savingCapacityService.saveCapacity(saving);
        return ResponseEntity.ok(savingCapacity);
    }








    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable Long id) throws Exception{
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
