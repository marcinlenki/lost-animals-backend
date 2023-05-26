package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.RoleService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.Role;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RolesControllers {
    private final RoleService roleService;

    @GetMapping
    public Page<Role> getRoleList(Pageable pageable) {
        return roleService.list(pageable);
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
