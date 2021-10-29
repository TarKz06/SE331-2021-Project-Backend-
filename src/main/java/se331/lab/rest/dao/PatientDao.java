package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Patient;

public interface PatientDao {
    Integer getPatientSize();
    Page<Patient> getPatients(Integer pageSize, Integer page);
    Patient getPatient(Long id);

    Patient save(Patient patient);
    Page<Patient> getPatient(String name, Pageable page);
}
