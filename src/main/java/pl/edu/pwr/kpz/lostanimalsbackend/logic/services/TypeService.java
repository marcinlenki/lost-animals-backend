package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.TypeRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Type;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public List<Type> getTypeList(){
        return this.typeRepository.findAll();
    }

    public Type getTypeById(Integer id){
        return this.typeRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "type with id: " + id + " dose not exists"
                ));
    }


    public void addType(Type type){
        typeRepository.save(type);
    }

    public void deleteTypeById(Integer id){
        boolean exists = typeRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("type with id:" + id + " dose not exists");
        }
        typeRepository.deleteById(id);
    }

    //TODO write update
    public void updateType(Integer id){
        Type type = this.typeRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "type with id: " + id + " dose not exists"
                ));


    }
}
