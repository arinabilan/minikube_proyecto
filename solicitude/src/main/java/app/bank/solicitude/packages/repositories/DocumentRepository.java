package app.bank.solicitude.packages.repositories;

import app.bank.solicitude.packages.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    public DocumentEntity findDocumentById(long id);
    public DocumentEntity findDocumentByTitle(String title);
    public ArrayList<DocumentEntity> findDocumentsByMinimunRequirements(Boolean minimunRequirements);
}
