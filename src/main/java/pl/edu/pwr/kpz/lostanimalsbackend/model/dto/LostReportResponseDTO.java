package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import org.hibernate.Hibernate;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;

import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LostReportResponseDTO {
    private int id;
    private Date lostDate;
    private double x;
    private double y;
    private String description;

    private int animalId;
    private int reportStatusId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LostReportResponseDTO lostReportResponseDTO = (LostReportResponseDTO) o;
        return getId() != 0 && Objects.equals(getId(), lostReportResponseDTO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
