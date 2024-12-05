package app.bank.solicitude.packages.repositories;

import app.bank.solicitude.packages.entities.SolicitudeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudeRepository extends JpaRepository<SolicitudeEntity, Long> {
    @Query(value = "SELECT * FROM solicitudes WHERE solicitudes.id = :id", nativeQuery = true)
    SolicitudeEntity findBySolicitudeId(@Param("id") Long id);

    public List<SolicitudeEntity> findByClientId(Long id);
}
