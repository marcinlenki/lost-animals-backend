package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;

import java.util.Date;

@Data
public class LostReportRequestDTO {
    private Date lostDate;
    private Coordinate coordinate;
    private String description;

    private int animalId;
    private int reportStatusId;
}
