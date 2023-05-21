package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "report_status", schema = "public", catalog = "lost_animals")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class ReportStatus extends DatabaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    private String name;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        ReportStatus that = (ReportStatus) o;
//        return getId() != 0 && Objects.equals(getId(), that.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}