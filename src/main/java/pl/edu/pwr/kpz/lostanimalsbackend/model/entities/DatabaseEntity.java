package pl.edu.pwr.kpz.lostanimalsbackend.model.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

//    @Override
//    @JsonIgnore
//    public int getIdentity() {
//        return id;
//    }
//
//    @Override
//    public void setNewIdentity(int newIdentity) {
//        this.id = newIdentity;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Breed breed = (Breed) o;
        return getId() != 0 && Objects.equals(getId(), breed.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
