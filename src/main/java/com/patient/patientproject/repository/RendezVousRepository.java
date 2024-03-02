package com.patient.patientproject.repository;

import com.patient.patientproject.entities.RendezVous;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
}
