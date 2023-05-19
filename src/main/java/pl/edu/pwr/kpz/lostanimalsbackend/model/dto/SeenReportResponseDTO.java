package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SeenReportResponseDTO {
    private Date lostDate;
    private Coordinate coordinate;
    private String description;

    private int userId;
    private int animalId;
}
