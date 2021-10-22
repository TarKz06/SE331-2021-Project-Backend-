package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
//    List<Patient> findAll();
//    Page<Patient> findByTitle(String title, Pageable pageRequest);
//    Page<Patient> findByTitleContaining(String title, Pageable pageRequest);
//    Page<Patient> findByTitleContainingOrDescriptionContaining(String title, String description, Pageable pageRequest);
//    Page<Patient> findByTitleContainingAndDescriptionContaining(String title, String description, Pageable pageRequest);
//    Page<Patient> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrDoctor_NameIgnoreCaseContaining(String title, String description, String organizerName, Pageable pageRequest);
//

}
