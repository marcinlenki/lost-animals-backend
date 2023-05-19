package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SeenReportRequestDTO {
    private int id;
    private UserRequestDTO user;
    private AnimalRequestDTO animal;
    private Coordinate coordinate;
    private Date seenDate;
    private String description;
}

