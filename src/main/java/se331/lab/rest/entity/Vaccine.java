package se331.lab.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String vname;
    String vtype;

    @ManyToMany
    @Builder.Default
    List<Patient> patientVaccineHistory = new ArrayList<>();

    @ElementCollection
    List<String> imageUrls;
}
