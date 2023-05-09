package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BreedRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Breed;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BreedService {

    private final BreedRepository breedRepository;

    public List<Breed> getBreedRepositoryList(){
        return this.breedRepository.findAll();
    }

    public Breed getBreedById(Integer id){
        return this.breedRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "breed with id: " + id + " dose not exists"
                ));
    }

    public void addBreed(Breed breed){
        this.breedRepository.save(breed);
    }

    public void deleteBreedById(Integer id){
        boolean exists = breedRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("breed with id:" + id + " dose not exists");
        }
        breedRepository.deleteById(id);
    }

    //TODO write update
    public void updateBreed(Integer id){
        Breed breed = this.breedRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "breed with id: " + id + " dose not exists"
                ));
    }
}
