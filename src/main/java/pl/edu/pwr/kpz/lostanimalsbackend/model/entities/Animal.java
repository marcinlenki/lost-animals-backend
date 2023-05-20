package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.Objects;

@Table(name = "animal", schema = "public", catalog = "lost_animals")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonManagedReference
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
    private List<AnimalPicture> animalPictures;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Animal animal = (Animal) o;
        return getId() != 0 && Objects.equals(getId(), animal.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}