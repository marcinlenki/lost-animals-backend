package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "role", schema = "public", catalog = "lost_animals")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Role extends DatabaseEntity {

    private String name;

}
