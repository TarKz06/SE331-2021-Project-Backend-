package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.dao.DoctorDao;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.entity.Doctor;

import javax.transaction.Transactional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientDao patientDao;
    @Autowired
    DoctorDao doctorDao;

    @Override
    public Integer getPatientSize() {
        return patientDao.getPatientSize();
    }

    @Override
    public Page<Patient> getPatients(Integer pageSize, Integer page) {
        return patientDao.getPatients(pageSize, page);
    }

    @Override
    public Patient getPatient(Long id) {
        return patientDao.getPatient(id);
    }

    @Override
    @Transactional
    public Patient save(Patient patient) {
        Doctor doctor = doctorDao.findById(patient.getDoctor().getId()).orElse(null);
        patient.setDoctor(doctor);
        doctor.getOwnPatients().add(patient);
        return patientDao.save(patient);
    }

    @Override
    public Page<Patient> getPatients(String title, Pageable pageable) {
        return patientDao.getPatient(title,pageable);
    }
}
