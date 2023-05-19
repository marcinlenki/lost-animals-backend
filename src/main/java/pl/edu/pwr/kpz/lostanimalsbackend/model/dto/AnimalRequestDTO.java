package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalColor;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Breed;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Sex;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnimalRequestDTO {
    private int id;
    private String chip;
    private String name;
    private Breed breed;
    private AnimalColor color;
    private Sex sex;
    private int idOwner;
}
