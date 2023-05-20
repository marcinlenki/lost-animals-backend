package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Coordinate;

import java.util.Date;

@Data
public class SeenReportRequestDTO {
    @Past(message = "The date should be in the past ", groups = SeenReportRequestInfo.class)
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date lostDate;
    @NotNull(message = "Seen report coordinate shouldn't be null", groups = SeenReportRequestInfo.class)
    private Coordinate coordinate;
    @NotNull(message = "Seen report description shouldn't be null", groups = SeenReportRequestInfo.class)
    private String description;
    @Min(value = 1, groups = SeenReportRequestInfo.class, message = "Wrong user id in seen report")
    private int userId;
    @Valid
    @NotNull(message = "Seen report animal shouldn't be null")
    private AnimalRequestDTO animal;
}
