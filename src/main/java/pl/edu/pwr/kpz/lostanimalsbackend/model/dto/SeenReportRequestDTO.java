package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;

import java.util.Date;
import java.util.Objects;

@Data
public class SeenReportRequestDTO {
    @Past(message = "The date should be in the past ", groups = SeenReportRequestInfo.class)
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date lostDate;
    @NotNull(message = "Seen report coordinate shouldn't be null", groups = SeenReportRequestInfo.class)
    private Coordinate coordinate;
    private String description;
    @Min(value = 1, message = "User id must be over 0 in seen report", groups = SeenReportRequestInfo.class)
    private int userId;

    // passed if there is an exact match
    @Min(value = 1, message = "Animal id must be over 0 in seen report", groups = SeenReportRequestInfo.class)
    private Integer animalId;

    // passed if there is no exact match and new "temporary" animal must be created
    @Valid
    private AnimalRequestDTO animal;

    // at least one of animal info (animalId, animal) must bre present
    @JsonIgnore
    @SuppressWarnings("unused") // used during validation
    @AssertTrue(message = "You must include some animal information!", groups = SeenReportRequestInfo.class)
    public boolean isValid() {
        return Objects.nonNull(animalId) || Objects.nonNull(animal);
    }
}
