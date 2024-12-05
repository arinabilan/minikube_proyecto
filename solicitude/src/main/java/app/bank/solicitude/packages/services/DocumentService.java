package app.bank.solicitude.packages.services;

import app.bank.solicitude.packages.entities.DocumentEntity;
import app.bank.solicitude.packages.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public ArrayList<DocumentEntity> getAllDocuments() {
        return (ArrayList<DocumentEntity>) documentRepository.findAll();
    }

    public DocumentEntity findDocumentById(Long id){
        return documentRepository.findDocumentById(id);
    }

    public DocumentEntity findDocumentByTitle(String title){
        return documentRepository.findDocumentByTitle(title);
    }
    public ArrayList<DocumentEntity> findDocumentByMinimunRequirements(Boolean minimunRequirements){
        return documentRepository.findDocumentsByMinimunRequirements(minimunRequirements);
    }

    public List<Long> findDocumentByMinimunRequirements1(Boolean minimunRequirements){
        ArrayList<DocumentEntity> documents =  documentRepository.findDocumentsByMinimunRequirements(minimunRequirements);
        List<Long> documentIds1 = documents.stream()
                .map(DocumentEntity::getId)
                .collect(Collectors.toList());

        return documentIds1;
    }

    public DocumentEntity save(DocumentEntity document){
        return documentRepository.save(document);
    }

    public Boolean deleteDocument(Long id) throws Exception{
        try{
            documentRepository.deleteById(id);
            return (Boolean) true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
