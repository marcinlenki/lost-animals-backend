package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;

    private int roleId;
}
