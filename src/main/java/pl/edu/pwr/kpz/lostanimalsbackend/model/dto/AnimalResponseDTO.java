package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

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
    private String name;
    private String chip;
    private Sex sex;

    private int userId;
    private int animalColorId;
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
