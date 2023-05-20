package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Sex;

@Data
public class AnimalRequestDTO {
    @NotBlank(message = "animal name shouldn't be blank")
    private String name;
    private String chip;
    @NotNull(message = "animal sex shouldn't be null")
    private Sex sex;

    @Min(value = 1)
    private int ownerId;
    @Min(value = 1, groups = SeenReportRequestInfo.class)
    private int animalColorId;
    @Min(value = 1, groups = SeenReportRequestInfo.class)
    private int breedId;
}
