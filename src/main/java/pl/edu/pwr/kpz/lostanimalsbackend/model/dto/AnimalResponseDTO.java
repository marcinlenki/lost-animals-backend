package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Animal;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Sex;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnimalResponseDTO {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String chip;
    @NotNull
    private Sex sex;

    private int ownerId;
    @NotNull
    private int animalColorId;
    @NotNull
    private int breedId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnimalResponseDTO animalResponseDTO = (AnimalResponseDTO) o;
        return getId() != 0 && Objects.equals(getId(), animalResponseDTO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
