package br.com.workmanagementW.controller;

import br.com.workmanagementW.dto.RegisterRoleDTO;
import br.com.workmanagementW.dto.RegisterWorkDTO;
import br.com.workmanagementW.service.RoleService;
import br.com.workmanagementW.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity createRole(@RequestBody RegisterRoleDTO dto, UriComponentsBuilder uriBuilder){

        var role = roleService.createRole(dto);
        URI uri = uriBuilder.path("/role/{id}").buildAndExpand(role.id()).toUri();
        return ResponseEntity.created(uri).body(role);

    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<RegisterRoleDTO>> findAllRole(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(roleService.findAllRole(pageable));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

    //@PatchMapping
    //public ResponseEntity addEmployeeInWork(@RequestBody List<RegisterEmployeeInWorkDTO> dto) {
    //}

}
