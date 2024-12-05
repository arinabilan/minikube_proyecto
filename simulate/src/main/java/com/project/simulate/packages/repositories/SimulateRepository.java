package com.project.simulate.packages.repositories;

import com.project.simulate.packages.entities.SimulateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulateRepository extends JpaRepository<SimulateEntity, Long> {

}
