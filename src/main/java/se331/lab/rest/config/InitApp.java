package se331.lab.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.*;
import se331.lab.rest.repository.*;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    VaccineRepository vaccineRepository;
    @Autowired
    CommentRepository commentRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Doctor doc1, doc2, doc3;
        doc1 = doctorRepository.save(Doctor.builder()
                .firstname("DOC1").build());
        doc2 = doctorRepository.save(Doctor.builder()
                .firstname("DOC2").build());
        doc3 = doctorRepository.save(Doctor.builder()
                .firstname("DOC3").build());

        User user1, user2, user3, user4;
        user1 = userRepository.save(User.builder()
                .username("user1")
                .password("user")
                .firstname("user1")
                .lastname("user1")
                .enabled(true)
                .build());
        user2 = userRepository.save(User.builder()
                .username("user2")
                .password("user")
                .firstname("user2")
                .lastname("user2")
                .enabled(true)
                .build());
        user3 = userRepository.save(User.builder()
                .username("user3")
                .password("user")
                .firstname("user3")
                .lastname("user3")
                .enabled(true)
                .build());
        user4 = userRepository.save(User.builder()
                .username("user4")
                .password("user")
                .firstname("user4")
                .lastname("user4")
                .enabled(true)
                .build());

        Admin admin1;
        admin1 = adminRepository.save(Admin.builder()
                .name("ADMIN1").build());

        Vaccine vaccine1, vaccine2;
        vaccine1 = vaccineRepository.save(Vaccine.builder()
                .vname("AstraZeneca")
                .vtype("Adenovirus vaccine")
                .build());
        vaccine2 = vaccineRepository.save(Vaccine.builder()
                .vname("Pfizer")
                .vtype("mRNA-based vaccine")
                .build());

        Patient patient1, patient2, patient3, patient4;
        patient1 = patientRepository.save(Patient.builder()
                .firstname("Caelan")
                .lastname("Cole")
                .age(32)
                .gender("Male")
                .hometown("Prejmer Romania")
                .status(0)
                .build());
        patient1.setDoctor(doc1);
        vaccine1.getPatientVaccineHistory().add(patient1);
        vaccine2.getPatientVaccineHistory().add(patient1);
        doc1.getOwnPatients().add(patient1);
        patient2 = patientRepository.save(Patient.builder()
                .firstname("Emillie")
                .lastname("Kinney")
                .age(16)
                .gender("Female")
                .hometown("Piskivka Ukraine")
                .status(1)
                .build());
        patient2.setDoctor(doc1);
        doc1.getOwnPatients().add(patient2);
        vaccine1.getPatientVaccineHistory().add(patient2);
        patient3 = patientRepository.save(Patient.builder()
                .firstname("Tomasz")
                .lastname("Burnett")
                .age(25)
                .gender("Male")
                .hometown("Nahuizalco El Salvador")
                .status(1)
                .build());
        patient3.setDoctor(doc2);
        vaccine1.getPatientVaccineHistory().add(patient3);
        doc2.getOwnPatients().add(patient3);
        patient4 = patientRepository.save(Patient.builder()
                .firstname("Honor")
                .lastname("Cervantes")
                .age(25)
                .gender("Female")
                .hometown("Peace River Canada")
                .status(2)
                .build());
        patient4.setDoctor(doc3);
        vaccine2.getPatientVaccineHistory().add(patient4);
        doc3.getOwnPatients().add(patient4);

        addUser();
        patient1.setUser(userPatient1);
        userPatient1.setPatient(patient1);
        patient2.setUser(userPatient2);
        userPatient2.setPatient(patient2);
        patient3.setUser(userPatient3);
        userPatient3.setPatient(patient3);
        patient4.setUser(userPatient4);
        userPatient4.setPatient(patient4);

        doc1.setUser(userDoc1);
        userDoc1.setDoctor(doc1);

        admin1.setUser(admin);
        admin.setAdmin(admin1);

        doc2.setUser(userDoc2);
        userDoc2.setDoctor(doc2);

        commentRepository.save(
                Comment.builder()
                        .comment_by(doc1)
                        .comment_to(patient1)
                        .content("pai doo tood")
                        .build()
        );
    }

    User userPatient1, userPatient2, userPatient3, userPatient4, admin, userDoc1, userDoc2;

    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        Authority authPatient = Authority.builder().name(AuthorityName.ROLE_PATIENT).build();

        userPatient1 = User.builder()
                .username("patient1")
                .password(encoder.encode("user"))
                .firstname("Caelan")
                .lastname("Cole")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        userPatient2 = User.builder()
                .username("patient2")
                .password(encoder.encode("user"))
                .firstname("Emillie")
                .lastname("Kinney")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        userPatient3 = User.builder()
                .username("patient3")
                .password(encoder.encode("user"))
                .firstname("Honor")
                .lastname("Cervantes")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        userPatient4 = User.builder()
                .username("patient4")
                .password(encoder.encode("user"))
                .firstname("Jacob")
                .lastname("Talley")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        userDoc1 = User.builder()
                .username("doc1")
                .password(encoder.encode("doctor"))
                .firstname("DOC1")
                .lastname("DOC1")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        userDoc2 = User.builder()
                .username("doc2")
                .password(encoder.encode("doctor"))
                .firstname("DOC2")
                .lastname("DOC2")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

//                authorityRepository.save(authUser);
//                authorityRepository.save(authAdmin);
//                authorityRepository.save(authDoctor);
//                authorityRepository.save(authPatient);

        userPatient1.getAuthorities().add(authPatient);
        userPatient2.getAuthorities().add(authPatient);
        userPatient3.getAuthorities().add(authPatient);
        userPatient4.getAuthorities().add(authPatient);
        admin.getAuthorities().add(authUser);
        admin.getAuthorities().add(authAdmin);
        userDoc1.getAuthorities().add(authDoctor);
        userDoc2.getAuthorities().add(authDoctor);
        userRepository.save(userPatient1);
        userRepository.save(userPatient2);
        userRepository.save(userPatient3);
        userRepository.save(userPatient4);
        userRepository.save(admin);


    }
}
