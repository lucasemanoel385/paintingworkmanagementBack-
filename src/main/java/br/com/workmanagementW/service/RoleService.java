package br.com.workmanagementW.service;

import br.com.workmanagementW.dto.RegisterRoleDTO;
import br.com.workmanagementW.entity.Role;
import br.com.workmanagementW.repository.RepositoryRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RepositoryRole repositoryRole;


    public RegisterRoleDTO createRole(RegisterRoleDTO dto) {
        var role = new Role(dto);
        repositoryRole.save(role);
        return new RegisterRoleDTO(role);
    }

    public Page<RegisterRoleDTO> findAllRole(Pageable pageable) {
        return repositoryRole.findAll(pageable).map(RegisterRoleDTO::new);
    }

    public void deleteRole(Long id) {
        repositoryRole.deleteById(id);
    }
}
