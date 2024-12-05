package app.bank.solicitude.packages.controllers;

import app.bank.solicitude.packages.entities.ClientDocumentEntity;
import app.bank.solicitude.packages.services.ClientDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/clientdocuments")
public class ClientDocumentController {
    @Autowired
    private ClientDocumentService clientDocumentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ClientDocumentEntity>> findByClientId(@PathVariable Long id) {
        List<ClientDocumentEntity> clientDocuments = clientDocumentService.findByClientId(id);
        return ResponseEntity.ok(clientDocuments);
    }

    @GetMapping("/listdocument/{clientId}")
    public ResponseEntity<List<Long>> getDocumentsIdsByClientId(@PathVariable Long clientId) {
        List<Long> ids = clientDocumentService.getDocumentIds(clientId);
        return ResponseEntity.ok(ids);
    }

    @GetMapping("/{clientId}/{title}")
    public ResponseEntity<ClientDocumentEntity> findByClientIdAndTitle(@PathVariable Long clientId, @PathVariable String title){
        ClientDocumentEntity clientDocument = clientDocumentService.findByClientIdAndTitle(clientId, title);
        return ResponseEntity.ok(clientDocument);
    }

    @PostMapping("/")
    public ResponseEntity<ClientDocumentEntity> saveClientDocument(@RequestBody ClientDocumentEntity document){
        ClientDocumentEntity clientDocument = clientDocumentService.saveClientDocument(document);
        return ResponseEntity.ok(clientDocument);
    }

    // Requiere que se envíe el cliente_id, el document_id y el archivo (FormData.append('file', filedata) file nombre de campo, filedata lo del campo file)
    /*
     * {
     *   "client_id": 1,
     *   "document_type": 1,
     *   "file": ...
     * }
     * */

    @PostMapping("/file/{client_id}/{document_type}")
    public ResponseEntity<Boolean> saveClientDocumentWithFile(@PathVariable Long client_id, @PathVariable Long document_type, @RequestParam("file") MultipartFile file) throws Exception {
        Boolean clientDocumentUploaded = clientDocumentService.uploadClientDocument(client_id, document_type, file);
        return ResponseEntity.ok(clientDocumentUploaded);
    }

    @PutMapping("/")
    public ResponseEntity<ClientDocumentEntity> updateClientDocument(@RequestBody ClientDocumentEntity document){
        ClientDocumentEntity clientDocument = clientDocumentService.updateClientDocument(document);
        return ResponseEntity.ok(clientDocument);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClientDocument(@PathVariable Long id) throws Exception{
        clientDocumentService.deleteClientDocument(id);
        return ResponseEntity.noContent().build();

    }
}
