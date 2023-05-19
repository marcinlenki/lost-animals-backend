package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRequestDTO {
    private int id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;

    private List<AnimalRequestDTO> animals;
}
