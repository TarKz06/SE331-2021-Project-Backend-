package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    Long id;
    String name;
    String surname;
    Integer age;
    String gender;
    String hometown;
    Integer status;
    Doctor doctor;
    List<Vaccine> vaccines;
    List<String> imageUrls;
}
