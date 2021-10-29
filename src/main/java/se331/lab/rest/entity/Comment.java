package se331.lab.rest.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String message;

    @ManyToOne
    Patient patientComment;

    @ManyToOne
    Doctor doctorComment;

}
