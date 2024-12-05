package app.bank.solicitude.packages.controllers;

import app.bank.solicitude.packages.entities.DocumentEntity;
import app.bank.solicitude.packages.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/")
    public ResponseEntity<List<DocumentEntity>> getAllDocuments() {
        List<DocumentEntity> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentEntity> findDocumentById(@PathVariable Long id){
        DocumentEntity document = documentService.findDocumentById(id);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/byMinimun/{minimum}")
    public ResponseEntity<ArrayList<DocumentEntity>> findDocumentByMinimunRequirements(@PathVariable Boolean minimum){
        ArrayList<DocumentEntity> document = documentService.findDocumentByMinimunRequirements(minimum);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<DocumentEntity> findDocumentByTittle(@PathVariable String title){
        DocumentEntity document = documentService.findDocumentByTitle(title);
        return ResponseEntity.ok(document);
    }
    @PostMapping("/")
    public ResponseEntity<DocumentEntity> createDocument(@RequestBody DocumentEntity document){
        DocumentEntity documento = documentService.save(document);
        return ResponseEntity.ok(documento);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DocumentEntity> updateDocument(@RequestBody DocumentEntity document){
        DocumentEntity documento = documentService.save(document);
        return ResponseEntity.ok(documento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDocument(@PathVariable Long id) throws Exception{
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byMinimun/minimum/{minimum}")
    public ResponseEntity<List<Long>> findDocumentByMinimunRequirements1(@PathVariable Boolean minimum){
        List<Long> document = documentService.findDocumentByMinimunRequirements1(minimum);
        return ResponseEntity.ok(document);
    }
}
