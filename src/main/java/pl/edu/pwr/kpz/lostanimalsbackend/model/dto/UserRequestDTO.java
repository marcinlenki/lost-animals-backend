package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = "name shouldn,t be empty")
    private String name;
    @NotBlank
    private String lastName;
    @NotNull
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;
    @Email
    private String email;
    @Min(value = 1)
    private int roleId;
}
