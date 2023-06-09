package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Role;

@Service
public class RoleService extends SimpleCrudService<Role> {

    public RoleService(BaseRepository<Role> repository) {
        super(repository, LoggerFactory.getLogger(RoleService.class));
    }

}
