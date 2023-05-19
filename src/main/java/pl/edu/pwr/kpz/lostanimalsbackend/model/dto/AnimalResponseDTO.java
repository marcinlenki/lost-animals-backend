package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Sex;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnimalResponseDTO {
    private String name;
    private String chip;
    private Sex sex;

    private int ownerId;
    private int animalColorId;
    private int breedId;
}
