package com.patient.patientproject;

import com.patient.patientproject.entities.*;
import com.patient.patientproject.repository.ConsultationRepository;
import com.patient.patientproject.repository.MedecinRepository;
import com.patient.patientproject.repository.PatientRepository;
import com.patient.patientproject.repository.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientProjectApplication {


    public static void main(String[] args) {

        SpringApplication.run(PatientProjectApplication.class, args);
    }

  @Bean
    @Transactional
    CommandLineRunner start(PatientRepository patientrepository,
                            MedecinRepository medecinRepository,
                            ConsultationRepository consultationRepository,
                            RendezVousRepository rendezVousRepository
                            ){

      return args -> {
          //Ajouter
          Patient patient = new Patient();
          patient.setId(1);
          patient.setNom("Zaid");
          patient.setMalade(true);
          patient.setDateNaissance(new Date());
          patient.setScore(10);
          patientrepository.save(patient);
          Patient patient3 = new Patient();
          patient3.setId(2);
          patient3.setNom("Ayoub");
          patient3.setMalade(true);
          patient3.setDateNaissance(new Date());
          patient3.setScore(7);
          patientrepository.save(patient3);
          Medecin medecin3 = new Medecin();
          medecin3.setId(1);
          medecin3.setEmail("Soulaymane.cherr@gmail.com");
          medecin3.setNom("Soulaimane");
          medecin3.setSpecialite("Cardio");
          medecinRepository.save(medecin3);

          //Consulter tous les patients:
          List<Patient> patients = patientrepository.findAllWithRendezvous(); // Utiliser une méthode personnalisée pour charger les rendezvous
          patients.forEach(p->{
              System.out.println(p.toString());
          });

          //chercher un patient:
          Patient patient2 = patientrepository.findById(Long.valueOf(1)).orElse(null);
          System.out.println("*********");
          System.out.println(patient2.getId());
          System.out.println(patient2.getNom());
          System.out.println("*********");
          List<Patient> o = patientrepository.findByNomContains("Sou");
          o.forEach(p->{
              System.out.println(p.toString());
          });

          //Mettre a jour patient:
          patientrepository.updatePatientByNom("Zaid","Mouakkal");

          //Supprimer Patient:
          patientrepository.deletePatientByNom("Ayoub");

          //d'autres operations:
          //rendez vous:
          Patient patient5 = patientrepository.findById(Long.valueOf(1)).orElse(null);
          Medecin medecin2=medecinRepository.findByNom("Soulaimane");
          RendezVous rendezVous = new RendezVous();
          rendezVous.setPatient(patient5);
          rendezVous.setMedecin(medecin2);
          rendezVous.setDate(new Date());
          rendezVous.setAnnule(false);
          rendezVous.setStatus(StatusRDV.DONE);

          //Consultation:
          Consultation consultation = new Consultation();
          consultation.setRapport("Test");
          consultation.setDateConsultation(rendezVous.getDate());
          consultation.setRendezVous(rendezVous);


          rendezVousRepository.save(rendezVous);
          consultationRepository.save(consultation);








      };
  }
}
