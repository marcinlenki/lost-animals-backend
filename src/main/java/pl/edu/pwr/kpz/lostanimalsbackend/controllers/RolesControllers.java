package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.RoleService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Role;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RolesControllers {
    private final RoleService roleService;

    @GetMapping
    public List<Role> getReportStatusList(){
        return this.roleService.getRoleList();
    }

    @GetMapping(path = "/{id}")
    public Role getReportStatusById(@PathVariable("id") Integer id){
        return this.roleService.getRoleById(id);
    }

    @PostMapping
    public void addReportStatus(@RequestBody Role reportStatus){
        roleService.addRole(reportStatus);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteReportStatusById(@PathVariable("id") Integer id){
        roleService.deleteRoleById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateReportStatus(@PathVariable("id") Integer id, @RequestBody Role reportStatus){
        roleService.updateRole(id, reportStatus);
    }
}
