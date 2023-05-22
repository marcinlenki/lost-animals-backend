package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Table(name = "animal", schema = "public", catalog = "lost_animals")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Animal extends DatabaseEntity {

    public Animal(int id) {
        super(id);
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @NaturalId
    private String chip;

    private String name;

    @ManyToOne
    @JoinColumn(name = "breed_id", referencedColumnName = "id")
    private Breed breed;

    @ManyToOne
    @JoinColumn(name = "animal_color_id", referencedColumnName = "id")
    private AnimalColor color;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(mappedBy = "animal")
    @ToString.Exclude
    private List<AnimalPicture> animalPictures;

}
