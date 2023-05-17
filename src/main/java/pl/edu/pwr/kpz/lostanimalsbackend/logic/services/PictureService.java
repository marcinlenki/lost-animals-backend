package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.PictureRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Picture;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;

    public List<Picture> getPictureList(){
        return this.pictureRepository.findAll();
    }

    public Picture getPictureById(Integer id){
        return this.pictureRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "picture with id: " + id + " dose not exists"
                ));
    }


    public void addPicture(Picture picture){
        pictureRepository.save(picture);
    }

    public void deletePictureById(Integer id){
        boolean exists = pictureRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("picture with id:" + id + " dose not exists");
        }
        this.pictureRepository.deleteById(id);
    }

    public void updatePicture(Integer id, Picture picture){
        boolean exists = pictureRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("picture with id:" + id + " dose not exists");
        }
        this.pictureRepository.save(picture);


    }
}
