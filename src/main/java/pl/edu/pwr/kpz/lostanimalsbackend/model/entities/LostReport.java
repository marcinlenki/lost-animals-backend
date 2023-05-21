package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "lost_report", schema = "public", catalog = "lost_animals")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LostReport extends DatabaseEntity {

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "report_status_id", referencedColumnName = "id")
    private ReportStatus status;

    @Column(name = "date")
    private Date lostDate;

    private String description;

    @Embedded
    private Coordinate coordinate;

}