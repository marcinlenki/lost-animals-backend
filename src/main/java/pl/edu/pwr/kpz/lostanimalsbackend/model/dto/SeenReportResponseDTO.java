package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;

import java.util.Date;

@Data
public class SeenReportResponseDTO {
    private int id;
//    private UserResponseDTO user; //id imie nazwisko tele
    private int userId;
    private String userName;
    private String userLastName;
    private String userPhoneNumber;

    private AnimalResponseDTO animal;
    private Coordinate coordinate;
    private Date seenDate;
    private String description;
}

