package se331.lab.rest.entity;

import lombok.*;
import org.hibernate.annotations.ManyToAny;
import se331.lab.rest.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String firstname;
    String lastname;
    Integer age;
    String gender;
    String hometown;
    Integer status;

    @ManyToOne
    Doctor doctor;

    @ManyToMany(mappedBy = "patientVaccineHistory")
    List<Vaccine> vaccines;

    @ElementCollection
    List<String> imageUrls;

    @OneToOne
    User user;

    @OneToMany(mappedBy = "comment_to")
    List<Comment> doctor_suggestion;
}
