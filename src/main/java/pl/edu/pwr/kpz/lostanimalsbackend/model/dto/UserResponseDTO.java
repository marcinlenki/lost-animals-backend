package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDTO {
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;

    private int roleId;
}
