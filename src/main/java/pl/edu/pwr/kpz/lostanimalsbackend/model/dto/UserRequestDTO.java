package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;

@Data
public class UserRequestDTO {
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;

    private int roleId;
}
