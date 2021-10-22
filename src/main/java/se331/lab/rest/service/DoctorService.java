package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctor();
    Page<Doctor> getDoctor(Integer page, Integer pageSize);
}
