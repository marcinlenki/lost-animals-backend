package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.Data;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;

import java.util.Date;

@Data
public class SeenReportRequestDTO {
    private Date lostDate;
    private Coordinate coordinate;
    private String description;

    private int userId;
    private AnimalRequestDTO animal;
}
