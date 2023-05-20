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
    @Past(groups = SeenReportRequestInfo.class)
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date lostDate;
    @NotNull(groups = SeenReportRequestInfo.class)
    private Coordinate coordinate;
    @NotNull(groups = SeenReportRequestInfo.class)
    private String description;
    @Min(value = 1,groups = SeenReportRequestInfo.class)
    private int userId;
    @Valid
    private AnimalRequestDTO animal;
}
