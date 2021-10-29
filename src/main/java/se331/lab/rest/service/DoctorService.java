package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;

import java.util.List;

public interface DoctorService {
    Integer getDoctorSize();
    Page<Doctor> getDoctors(Integer pageSize, Integer page);
    Doctor getDoctor(Long id);

    Doctor save(Doctor doctor);

    Page<Patient> getPatientInCares(Doctor doctor, Integer pageSize, Integer page);

    Page<Doctor> getDoctors(String title, Pageable pageable);
}
