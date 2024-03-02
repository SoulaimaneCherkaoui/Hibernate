package com.patient.patientproject.repository;

import com.patient.patientproject.entities.Patient;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Transactional
public interface PatientRepository extends JpaRepository<Patient,Long> {
List<Patient> findByNomContains(String c);
    @Query("SELECT DISTINCT p FROM Patient p LEFT JOIN FETCH p.rendezvous")

    List<Patient> findAllWithRendezvous();
@Query("Select p from Patient p where p.nom like :x")
    List<Patient> search(@Param("x") String mc);
@Modifying
    @Query("UPDATE Patient p SET p.nom = :newNom WHERE p.nom = :nom")
    int updatePatientByNom(@Param("nom") String nom, @Param("newNom") String newNom);
    @Modifying
    @Query("DELETE FROM Patient p WHERE p.nom = :nom")
    int deletePatientByNom(@Param("nom") String nom);


}




