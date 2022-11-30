package sn.isi.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.Role;
import sn.isi.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    private RoleService roleService;

    @GetMapping
    public List<Role> getRole() {
        return roleService.getRoles();
    }

    @GetMapping("/{id}")
    public Role getRole(@PathVariable("id") int id) {
        return roleService.getRole(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Role createAppRoles(@Valid @RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable("id") int id, @Valid @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") int id)  {roleService.deleteRoles(id);}

}

