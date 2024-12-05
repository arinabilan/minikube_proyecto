package app.bank.solicitude.packages.services;

import app.bank.solicitude.packages.entities.ClientDocumentEntity;
import app.bank.solicitude.packages.entities.DocumentEntity;
import app.bank.solicitude.packages.repositories.ClientDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientDocumentService {
    @Autowired
    private ClientDocumentRepository clientDocumentRepository;//se conecta con la capa inferior que es repositorio

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileUploadService fileUploadService;

    public ArrayList<ClientDocumentEntity> findByClientId(Long id) {
        return clientDocumentRepository.findByClientId(id);
    }

    public List<Long> getDocumentIds(Long id) {
        List<ClientDocumentEntity> documentsClient = clientDocumentRepository.findByClientId(id);
        List<Long> documentIds = documentsClient.stream()
                .map(clientDocument -> clientDocument.getDocument().getId())
                .collect(Collectors.toList());
        return documentIds;
    }

    public ClientDocumentEntity findByClientIdAndTitle(Long clientId, String title){
        return clientDocumentRepository.findByClientIdAndTitle(clientId, title);
    }

    public ClientDocumentEntity saveClientDocument(ClientDocumentEntity document){
        return clientDocumentRepository.save(document);
    }

    public ClientDocumentEntity updateClientDocument(ClientDocumentEntity document){
        return clientDocumentRepository.save(document);
    }

    public Boolean deleteClientDocument(Long id) throws Exception{
        try{
            clientDocumentRepository.deleteById(id);
            return (Boolean) true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Boolean uploadClientDocument(Long client_id, Long document_type, MultipartFile file) throws Exception{
        String fileName = file.getOriginalFilename();
        byte[] fileContent = fileUploadService.uploadFile(file);
        ClientDocumentEntity clientDocumentEntity = new ClientDocumentEntity();
        //ClientEntity clientEntity = clientService.getById(client_id);
        DocumentEntity documentEntity = documentService.findDocumentById(document_type);
        clientDocumentEntity.setClientId(client_id);
        clientDocumentEntity.setDocument(documentEntity);
        clientDocumentEntity.setFechaCarga(LocalDate.now());
        clientDocumentEntity.setRutaDocumento(fileName);
        clientDocumentEntity.setDocumento(fileContent);
        clientDocumentEntity.setEstado(Boolean.valueOf(false));
        clientDocumentRepository.save(clientDocumentEntity);
        return (Boolean) true;
    }

    public ClientDocumentEntity downloadClientDocumentById(long id){
        return clientDocumentRepository.finByDocumentId(id);
    }
}
