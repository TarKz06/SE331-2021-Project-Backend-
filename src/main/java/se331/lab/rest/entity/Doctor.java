package se331.lab.rest.entity;

import lombok.*;
import se331.lab.rest.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String firstname;
    String lastname;
    String age;
    String gender;
    String hometown;
    Integer status;
    String imageUrls;
    @OneToMany(mappedBy = "doctor")
    @Builder.Default
    List<Patient> ownPatients = new ArrayList<>();
    @OneToOne(cascade=CascadeType.ALL)
    User user;

}
