package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.repository.PatientRepository;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Repository
public class UserDaoDbImpl implements UserDao{

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Integer getUserSize() {
        return Math.toIntExact(userRepository.count());
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUser(String name, Pageable page) {
        return null;
    }

    @Override
    public Doctor setRoleToDoctor(User user) {
        User tempDoc;
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String tempFirstName = user.getFirstname();
        String tempLastName = user.getFirstname();
        String tempPassword = user.getPassword();
        String tempUserName = user.getUsername();

        Doctor doc1;

        doc1 = doctorRepository.save(Doctor.builder()
                .firstname(tempFirstName).build());

        tempDoc = User.builder()
                .username(tempUserName)
                .password(encoder.encode(tempPassword))
                .firstname(tempFirstName)
                .lastname(tempLastName)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        userRepository.delete(user);
        doc1.setUser(tempDoc);
        tempDoc.setDoctor(doc1);
        tempDoc.getAuthorities().add(authDoctor);

        return doctorRepository.save(doc1);
    }

    @Override
    public Patient setRoleToPatient(User user) {
        User tempPatient;
        Authority authPatient = Authority.builder().name(AuthorityName.ROLE_PATIENT).build();
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String tempFirstName = user.getFirstname();
        String tempLastName = user.getFirstname();
        String tempPassword = user.getPassword();
        String tempUserName = user.getUsername();
        String tempAge = user.getAge();
        String tempGender = user.getHometown();
        String tempHometown = user.getHometown();
        String tempImageUrl = user.getImageUrl();

        Patient patient1;

        patient1 = patientRepository.save(Patient.builder()
                .firstname(tempFirstName)
                .lastname(tempLastName)
                .age(tempAge)
                .gender(tempGender)
                .hometown(tempHometown)
                .imageUrls(null)
                .status(0)
                .build());

        tempPatient = User.builder()
                .username(tempUserName)
                .password(encoder.encode(tempPassword))
                .firstname(tempFirstName)
                .lastname(tempLastName)
                .age(tempAge)
                .gender(tempGender)
                .hometown(tempHometown)
                .imageUrl(null)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        userRepository.delete(user);
        patient1.setUser(tempPatient);
        tempPatient.setPatient(patient1);
        tempPatient.getAuthorities().add(authPatient);

        return patientRepository.save(patient1);
    }
}
