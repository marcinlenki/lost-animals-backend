package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportRequestInfo;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Coordinate {
    @Min(value = -90, message = "x (latitude) must be greater than -90", groups = SeenReportRequestInfo.class)
    @Max(value = 90, message = "x (latitude) must be less than 90", groups = SeenReportRequestInfo.class)
    @Column(name = "geo_x")
    private double x;

    @Min(value = -180, message = "y (longitude) must be greater than -180", groups = SeenReportRequestInfo.class)
    @Max(value = 180, message = "y (longitude) must be less than 180", groups = SeenReportRequestInfo.class)
    @Column(name = "geo_y")
    private double y;

}
