package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;

import java.util.List;

@Data
public class UserResponseDTO {
    private int id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;

    private List<AnimalResponseDTO> animals;
}
