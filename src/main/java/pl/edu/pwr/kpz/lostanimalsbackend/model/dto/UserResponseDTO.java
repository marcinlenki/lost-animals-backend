package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDTO {
    private int id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserResponseDTO userResponseDTO = (UserResponseDTO) o;
        return getId() != 0 && Objects.equals(getId(), userResponseDTO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
