package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Doctor;

import java.util.Optional;

public interface DoctorDao {
    Page<Doctor> getDoctor(Pageable pageRequest);

    Optional<Doctor> findById(Long id);
}
