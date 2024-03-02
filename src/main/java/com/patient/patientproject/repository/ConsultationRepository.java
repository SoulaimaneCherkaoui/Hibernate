package com.patient.patientproject.repository;

import com.patient.patientproject.entities.Consultation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
