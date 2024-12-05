package app.bank.register.packages.controllers;

import app.bank.register.packages.entities.ClientEntity;
import app.bank.register.packages.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

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

    @PutMapping("/")
    public ResponseEntity<ClientEntity> updateClient(@RequestBody ClientEntity client){
        ClientEntity clientUpdated = clientService.updateClient(client);
        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable Long id) throws Exception{
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
