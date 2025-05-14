package br.com.workmanagementW.repository;

import br.com.workmanagementW.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRole extends JpaRepository<Role, Long> {
}
