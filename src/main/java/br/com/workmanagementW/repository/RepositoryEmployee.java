package br.com.workmanagementW.repository;


import br.com.workmanagementW.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryEmployee extends JpaRepository<Employee, Long> {

}
