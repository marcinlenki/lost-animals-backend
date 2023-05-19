package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.ReportStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LostReportRequestDTO {
    private int id;
    private AnimalRequestDTO animal;
    private ReportStatus status;
    private Date lostDate;
    private String description;
    private Coordinate coordinate;
}
