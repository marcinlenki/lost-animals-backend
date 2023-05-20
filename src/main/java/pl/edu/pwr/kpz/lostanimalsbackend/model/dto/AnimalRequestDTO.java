package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.Data;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Sex;

@Data
public class AnimalRequestDTO {
    private String name;
    private String chip;
    private Sex sex;

    private int ownerId;
    private int animalColorId;
    private int breedId;
}
