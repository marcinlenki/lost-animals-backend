package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "animal_picture", schema = "public", catalog = "lost_animals")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class AnimalPicture extends DatabaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    @ToString.Exclude
    @JsonIgnore
    private Animal animal;

    private String contentType;

    private String url;

}
