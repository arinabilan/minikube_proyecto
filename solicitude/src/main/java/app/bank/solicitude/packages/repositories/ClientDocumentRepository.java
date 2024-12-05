package app.bank.solicitude.packages.repositories;

import app.bank.solicitude.packages.entities.ClientDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ClientDocumentRepository extends JpaRepository<ClientDocumentEntity, Long> {
    public ArrayList<ClientDocumentEntity> findByClientId(Long id);

    @Query("SELECT cd FROM ClientDocumentEntity cd WHERE cd.clientId = :clientId AND cd.document.title = :title")
    public ClientDocumentEntity findByClientIdAndTitle(@Param("clientId") Long clientId, @Param("title") String title);

    @Query("SELECT cd FROM ClientDocumentEntity cd WHERE cd.id = :id")
    public ClientDocumentEntity finByDocumentId(@Param("id") Long id);
}
