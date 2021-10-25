package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.DoctorDao;
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    PatientDao patientDao;
    @Autowired
    DoctorDao doctorDao;

    @Override
    public Integer getDoctorSize() {
        return doctorDao.getDoctorSize();
    }

    @Override
    public Page<Doctor> getDoctors(Integer pageSize, Integer page) {
        return doctorDao.getDoctors(pageSize, page);
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorDao.getDoctor(id);
    }

    @Override
    @Transactional
    public Doctor save(Doctor doctor) {
        return null;
    }

    @Override
    public Page<Doctor> getDoctors(String title, Pageable pageable) {
        return doctorDao.getDoctor(title,pageable);
    }

}
