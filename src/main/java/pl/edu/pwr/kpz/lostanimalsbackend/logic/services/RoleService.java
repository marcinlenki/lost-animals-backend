package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.RoleRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Role;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getRoleList(){
        return this.roleRepository.findAll();
    }

    public Role getRoleById(Integer id){
        return this.roleRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "Role with id: " + id + " dose not exists"
                ));
    }


    public void addRole(Role role){
        roleRepository.save(role);
    }

    public void deleteRoleById(Integer id) {
        boolean exists = roleRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Role with id:" + id + " dose not exists");
        }
        this.roleRepository.deleteById(id);
    }

    public void updateRole(Integer id, Role role) {
        boolean exists = roleRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Role with id:" + id + " dose not exists");
        }

        this.roleRepository.save(role);
    }
}
