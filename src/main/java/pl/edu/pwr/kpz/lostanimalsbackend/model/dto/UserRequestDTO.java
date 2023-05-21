package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = "User name shouldn,t be blank")
    private String name;
    @NotBlank(message = "User last name shouldn,t be blank")
    private String lastName;
    @NotNull(message = "User phone number shouldn,t be null")
    @Pattern(regexp = "^\\d{9}$", message = "User phone number wrong pattern")
    private String phoneNumber;
    @Email(message = "User email wrong pattern")
    private String email;
    private String password;


    @Min(value = 1, message = "Role id must be over 0 in user")
    private int idRole;
}
