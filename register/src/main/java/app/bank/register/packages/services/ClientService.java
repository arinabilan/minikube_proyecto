package app.bank.register.packages.services;

import app.bank.register.packages.entities.ClientEntity;
import app.bank.register.packages.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ArrayList<ClientEntity> getClients(){
        return (ArrayList<ClientEntity>) clientRepository.findAll();
    }

    public ClientEntity getById(Long id) {
        Optional<ClientEntity> client = clientRepository.findById(id);
        return client.orElse(null); // Uso seguro de Optional
    }

    public ClientEntity findByEmailAndPassword(String email, String password){
        Optional<ClientEntity> optionalClient = clientRepository.findByEmail(email);

        if (optionalClient.isPresent()) {
            ClientEntity client = optionalClient.get();

            // Compara las contraseñas directamente
            if (client.getPassword().equals(password)) {
                return client; // Contraseña correcta
            } else {
                throw new IllegalArgumentException("Contraseña incorrecta");
            }
        } else {
            throw new IllegalArgumentException("Email no encontrado");
        }
    }

    public ClientEntity saveClient(ClientEntity client){
        return clientRepository.save(client);
    }

    public ClientEntity updateClient(ClientEntity client){
        return clientRepository.save(client);
    }

    public Boolean deleteClient(Long id) throws Exception{
        try{
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
