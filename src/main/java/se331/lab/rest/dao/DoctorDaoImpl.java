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

import java.util.GregorianCalendar;
import java.util.Optional;

@Repository
public class DoctorDaoImpl implements DoctorDao{

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PatientRepository patientRepository;
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
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Page<Doctor> getDoctor(String title, Pageable page) {
//        return doctorRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrDoctor_NameIgnoreCaseContaining(title,title,title,page);
        return null;
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Patient giveComment(String content, Doctor comment_by, Patient comment_to) {
        commentRepository.save(
                Comment.builder()
                        .content(content)
                        .comment_by(comment_by)
                        .comment_to(comment_to)
                        .build()
        );
        return patientRepository.save(comment_to);
    }
}
