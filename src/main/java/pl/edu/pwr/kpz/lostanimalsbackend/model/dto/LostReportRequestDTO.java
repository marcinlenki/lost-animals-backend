package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;

import java.util.Date;

@Data
public class LostReportRequestDTO {
    @Past(message = "The date should be in the past ")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date lostDate;
    @NotNull(message = "Lost report coordinate shouldn't be null")
    private Coordinate coordinate;
    @NotNull(message = "Lost report description shouldn't be null")
    private String description;

    @Min(value = 1, message = "Wrong animal id in lost report")
    private int animalId;
    @Min(value = 1, message = "Wrong report status id in lost report")
    private int reportStatusId;
}
