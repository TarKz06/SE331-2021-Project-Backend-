package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.repository.PatientRepository;

@Repository
public class PatientDaoDbImpl implements PatientDao {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Integer getPatientSize() {
        return Math.toIntExact(patientRepository.count());
    }

    @Override
    public Page<Patient> getPatients(Integer pageSize, Integer page) {
        return patientRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Page<Patient> getPatient(String title, Pageable page) {
//        return patientRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrDoctor_NameIgnoreCaseContaining(title,title,title,page);
        return null;
    }
}
