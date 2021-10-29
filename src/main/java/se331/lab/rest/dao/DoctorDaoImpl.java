package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Comment;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.repository.CommentRepository;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.repository.PatientRepository;

import java.util.Optional;

@Repository
public class DoctorDaoImpl implements DoctorDao{

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Integer getDoctorSize() {
        return Math.toIntExact(doctorRepository.count());
    }

    @Override
    public Page<Doctor> getDoctors(Integer pageSize, Integer page) {
        return doctorRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Patient> getPatientInCares(Doctor doctor, Integer pageSize, Integer page) {
        return patientRepository.findByDoctor(doctor,PageRequest.of(pageSize,page));
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Page<Doctor> getDoctor(String title, Pageable page) {
//        return doctorRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrDoctor_NameIgnoreCaseContaining(title,title,title,page);
        return null;
    }

    @Override
    public Patient doctorPostComment(String message, Patient patientComment, Doctor doctorComment){
        commentRepository.save( Comment.builder()
                                .message(message)
                                .doctorComment(doctorComment)
                                .patientComment(patientComment).build()
        );
        return patientComment;
    }
    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }
}
