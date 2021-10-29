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
    String age;
    String gender;
    String hometown;
    String imageUrl;
    Integer status;

    @ManyToOne
    Doctor doctor;

    @OneToMany(mappedBy = "patientVaccineHistory")
    @Builder.Default
    List<Vaccine> vaccines = new ArrayList<>();

    @OneToMany(mappedBy = "patientComment")
    List<Comment> receiveCommentList;

    String imageUrls;

    @OneToOne(cascade=CascadeType.ALL)
    User user;
}
