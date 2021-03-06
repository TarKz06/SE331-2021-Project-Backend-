package se331.lab.rest.security.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import se331.lab.rest.entity.Admin;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    private String lastname;

    @Column(name = "AGE", length = 10)
    @NotNull
    private String age;

    @Column(name = "GENDER", length = 10)
    @NotNull
    private String gender;

    @Column(name = "HOMETOWN", length = 50)
    @NotNull
    private String hometown;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @Column(name = "IMAGE_URL")
    @NotNull
    private String imageUrl;

	@Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Authority> authorities = new ArrayList<>();

    @OneToOne
    Doctor doctor;

    @OneToOne
    Admin admin;

    @OneToOne
    Patient patient;

}