package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@Table(name = "seen_report", schema = "public", catalog = "lost_animals")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SeenReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SeenReport that = (SeenReport) o;
        return getId() != 0 && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
