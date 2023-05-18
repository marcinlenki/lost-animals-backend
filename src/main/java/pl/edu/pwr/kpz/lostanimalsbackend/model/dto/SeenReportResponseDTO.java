package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SeenReportResponseDTO {
    private int id;
    @NotNull
    private Date lostDate;
    @NotNull
    private double x;
    @NotNull
    private double y;
    private String description;

    @NotNull
    private int userId;
    @NotNull
    private int animalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SeenReportResponseDTO seenReportResponseDTO = (SeenReportResponseDTO) o;
        return getId() != 0 && Objects.equals(getId(), seenReportResponseDTO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
