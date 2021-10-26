package se331.lab.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Admin;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.AdminRepository;
import se331.lab.rest.repository.PatientRepository;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        addUser();

        Admin admin1;
        admin1 = adminRepository.save(Admin.builder()
                .firstname("ADMIN1")
                .lastname("ADMIN1")
                .build());

        Doctor doc1, doc2, doc3;
        doc1 = doctorRepository.save(Doctor.builder()
                .firstname("DOC1")
                .lastname("DOCTOR1")
                .age("30")
                .gender("Male")
                .hometown("Alaska")
                .build());
        doc2 = doctorRepository.save(Doctor.builder()
                .firstname("DOC2")
                .lastname("DOCTOR2")
                .age("20")
                .gender("Male")
                .hometown("Baromia")
                .build());

        User user1, user2, user3, user4;
        user1 = userRepository.save(User.builder()
                .username("user1")
                .password("user")
                .firstname("user1")
                .lastname("user1")
                .age("12")
                .gender("Female")
                .hometown("Thailand")
                .enabled(true)
                .build());
        user2 = userRepository.save(User.builder()
                .username("user2")
                .password("user")
                .firstname("user2")
                .lastname("user2")
                .age("18")
                .gender("Female")
                .hometown("Australia")
                .enabled(true)
                .build());
        user3 = userRepository.save(User.builder()
                .username("user3")
                .password("user")
                .firstname("user3")
                .lastname("user3")
                .age("36")
                .gender("Male")
                .hometown("Japan")
                .enabled(true)
                .build());
        user4 = userRepository.save(User.builder()
                .username("user4")
                .password("user")
                .firstname("user4")
                .lastname("user4")
                .age("34")
                .gender("Male")
                .hometown("Russia")
                .enabled(true)
                .build());

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
                .age("32")
                .gender("Male")
                .hometown("Prejmer Romania")
                .imageUrl("https://data.whicdn.com/images/346071085/original.gif")
                .status(0)
                .build());
        patient1.setDoctor(doc1);
        vaccine1.getPatientVaccineHistory().add(patient1);
        vaccine2.getPatientVaccineHistory().add(patient1);
        doc1.getOwnPatients().add(patient1);
        patient2 = patientRepository.save(Patient.builder()
                .firstname("Emillie")
                .lastname("Kinney")
                .age("16")
                .gender("Female")
                .hometown("Piskivka Ukraine")
                .imageUrl("https://data.whicdn.com/images/346071093/original.gif")
                .status(1)
                .build());
        patient2.setDoctor(doc1);
        doc1.getOwnPatients().add(patient2);
        vaccine1.getPatientVaccineHistory().add(patient2);
        patient3 = patientRepository.save(Patient.builder()
                .firstname("Tomasz")
                .lastname("Burnett")
                .age("25")
                .gender("Male")
                .hometown("Nahuizalco El Salvador")
                .imageUrl("https://i.gifer.com/Lynk.gif")
                .status(1)
                .build());
        patient3.setDoctor(doc2);
        vaccine1.getPatientVaccineHistory().add(patient3);
        doc2.getOwnPatients().add(patient3);
        patient4 = patientRepository.save(Patient.builder()
                .firstname("Honor")
                .lastname("Cervantes")
                .age("25")
                .gender("Female")
                .hometown("Peace River Canada")
                .imageUrl("https://gifimage.net/wp-content/uploads/2017/09/anime-gif-300x300-200kb-8.gif")
                .status(2)
                .build());
        patient4.setDoctor(doc2);
        vaccine2.getPatientVaccineHistory().add(patient4);
        doc2.getOwnPatients().add(patient4);


        admin1.setUser(admin);
        admin.setAdmin(admin1);

        doc1.setUser(userDoc1);
        userDoc1.setDoctor(doc1);
        doc2.setUser(userDoc2);
        userDoc2.setDoctor(doc2);

        patient1.setUser(userPatient1);
        userPatient1.setPatient(patient1);
        patient2.setUser(userPatient2);
        userPatient2.setPatient(patient2);
        patient3.setUser(userPatient3);
        userPatient3.setPatient(patient3);
        patient4.setUser(userPatient4);
        userPatient4.setPatient(patient4);




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
                        .password(encoder.encode("patient"))
                        .firstname("Caelan")
                        .lastname("Cole")
                        .age("32")
                        .gender("Male")
                        .hometown("Prejmer Romania")
                        .imageUrl("https://data.whicdn.com/images/346071085/original.gif")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build();

                userPatient2 = User.builder()
                        .username("patient2")
                        .password(encoder.encode("patient"))
                        .firstname("Emillie")
                        .lastname("Kinney")
                        .age("16")
                        .gender("Female")
                        .hometown("Piskivka Ukraine")
                        .imageUrl("https://data.whicdn.com/images/346071093/original.gif")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build();

                userPatient3 = User.builder()
                        .username("patient3")
                        .password(encoder.encode("patient"))
                        .firstname("Tomasz")
                        .lastname("Burnett")
                        .age("25")
                        .gender("Male")
                        .hometown("Nahuizalco El Salvador")
                        .imageUrl("https://i.gifer.com/Lynk.gif")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build();

                userPatient4 = User.builder()
                        .username("patient4")
                        .password(encoder.encode("patient"))
                        .firstname("Honor")
                        .lastname("Cervantes")
                        .age("25")
                        .gender("Female")
                        .hometown("Peace River Canada")
                        .imageUrl("https://gifimage.net/wp-content/uploads/2017/09/anime-gif-300x300-200kb-8.gif")
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

                authorityRepository.save(authUser);
                authorityRepository.save(authAdmin);
                authorityRepository.save(authDoctor);
                authorityRepository.save(authPatient);

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
