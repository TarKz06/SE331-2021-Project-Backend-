package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Patient;

public interface PatientService {
    Integer getPatientSize();
    Page<Patient> getPatients(Integer pageSize, Integer page);
    Patient getPatient(Long id);

    Patient save(Patient patient);
    Page<Patient> getPatients(String title, Pageable pageable);
}
