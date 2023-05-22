package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.ReportStatus;

import java.util.Date;

@Data
public class LostReportResponseDTO {
    private int id;
    private AnimalResponseDTO animal;
    private ReportStatus status;
    private Date lostDate;
    private String description;
    private Coordinate coordinate;
}
