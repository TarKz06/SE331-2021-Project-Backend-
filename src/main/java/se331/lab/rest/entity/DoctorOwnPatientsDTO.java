package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorOwnPatientsDTO {
    Long id;
    String firstname;
    String lastname;
    String age;
    String gender;
    String hometown;
    Integer status;
    List<String> imageUrls;
}
