package se331.lab.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long comment_id;

    @ManyToOne
    Doctor comment_by;

    @ManyToOne
    Patient comment_to;

    String content;
}