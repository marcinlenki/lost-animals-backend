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
    public List<Role> getRoleList() {
        return roleService.list();
    }

    @GetMapping("{id}")
    public Role getRoleById(@PathVariable("id") int id) {
        return roleService.getOne(id);
    }

    @PostMapping
    public Role addRole(@RequestBody Role role) {
        return roleService.add(role);
    }

    @PutMapping("{id}")
    public Role updateRole(@PathVariable("id") int id, @RequestBody Role role) {
        return roleService.update(id, role);
    }

    @DeleteMapping("{id}")
    public void deleteRoleById(@PathVariable("id") int id) {
        roleService.delete(id);
    }
}
