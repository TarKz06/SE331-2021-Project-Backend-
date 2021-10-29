package se331.lab.rest.util.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestForm {

    String message;
    Long doctorComment;
    Long patientComment;
}
