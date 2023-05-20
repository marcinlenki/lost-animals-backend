package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import lombok.Data;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalColor;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Breed;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Sex;

import java.util.List;

@Data
public class AnimalResponseDTO {
    private int id;
    private String chip;
    private String name;
    private Breed breed;
    private AnimalColor color;
    private Sex sex;
    private int ownerId;
    private String ownerName;
    private String ownerLastName;
    private String ownerPhoneNumber;
    private List<AnimalPicture> animalPictures;
}
