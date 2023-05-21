package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "seen_report", schema = "public", catalog = "lost_animals")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class SeenReport extends DatabaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    @Embedded
    private Coordinate coordinate;

    @Column(name = "date")
    private Date seenDate;

    private String description;

}
