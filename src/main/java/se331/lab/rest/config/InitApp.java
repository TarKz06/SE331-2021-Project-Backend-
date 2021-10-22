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
        Doctor doc1, doc2, doc3;
        doc1 = doctorRepository.save(Doctor.builder()
                .name("DOC1").build());
        doc2 = doctorRepository.save(Doctor.builder()
                .name("DOC2").build());
        doc3 = doctorRepository.save(Doctor.builder()
                .name("DOC3").build());

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
                .name("Caelan")
                .surname("Cole")
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
                .name("Emillie")
                .surname("Kinney")
                .age(16)
                .gender("Female")
                .hometown("Piskivka Ukraine")
                .status(1)
                .build());
        patient2.setDoctor(doc1);
        doc1.getOwnPatients().add(patient2);
        vaccine1.getPatientVaccineHistory().add(patient2);
        patient3 = patientRepository.save(Patient.builder()
                .name("Tomasz")
                .surname("Burnett")
                .age(25)
                .gender("Male")
                .hometown("Nahuizalco El Salvador")
                .status(1)
                .build());
        patient3.setDoctor(doc2);
        vaccine1.getPatientVaccineHistory().add(patient3);
        doc2.getOwnPatients().add(patient3);
        patient4 = patientRepository.save(Patient.builder()
                .name("Honor")
                .surname("Cervantes")
                .age(25)
                .gender("Female")
                .hometown("Peace River Canada")
                .status(2)
                .build());
        patient4.setDoctor(doc3);
        vaccine2.getPatientVaccineHistory().add(patient4);
        doc3.getOwnPatients().add(patient4);

        addUser();
        patient1.setUser(user1);
        user1.setPatient(patient1);
        patient2.setUser(user2);
        user2.setPatient(patient2);
        doc3.setUser(user3);
        user3.setDoctor(doc3);
        patient4.setUser(user4);
        user4.setPatient(patient4);
        admin1.setUser(admin);
        admin.setAdmin(admin1);

    }

    User user1, user2, user3, user4, admin;
    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
                user1 = User.builder()
                        .username("patient1")
                        .password(encoder.encode("user"))
                        .firstname("Caelan")
                        .lastname("Cole")
                        .email("enabled@user.com")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build();

                user2 = User.builder()
                        .username("patient2")
                        .password(encoder.encode("user"))
                        .firstname("Emillie")
                        .lastname("Kinney")
                        .email("enabled@user.com")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build();

                user3 = User.builder()
                        .username("patient3")
                        .password(encoder.encode("user"))
                        .firstname("Honor")
                        .lastname("Cervantes")
                        .email("enabled@user.com")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build();

                user4 = User.builder()
                        .username("patient4")
                        .password(encoder.encode("user"))
                        .firstname("Jacob")
                        .lastname("Talley")
                        .email("enabled@user.com")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build();

                admin = User.builder()
                        .username("admin")
                        .password(encoder.encode("admin"))
                        .firstname("admin")
                        .lastname("admin")
                        .email("admin@admin.com")
                        .enabled(true)
                        .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build();

                authorityRepository.save(authUser);
                authorityRepository.save(authAdmin);
                user1.getAuthorities().add(authUser);
                user2.getAuthorities().add(authUser);
                user3.getAuthorities().add(authUser);
                user4.getAuthorities().add(authUser);
                admin.getAuthorities().add(authUser);
                admin.getAuthorities().add(authAdmin);
                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);
                userRepository.save(user4);
                userRepository.save(admin);
    }


}
