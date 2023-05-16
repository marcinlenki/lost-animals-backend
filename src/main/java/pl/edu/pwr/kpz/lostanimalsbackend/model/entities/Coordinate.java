package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Coordinate {
    @Column(name = "geo_x")
    private double x;

    @Column(name = "geo_y")
    private double y;

}
