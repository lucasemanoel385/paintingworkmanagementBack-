package br.com.workmanagementW.service;

import br.com.workmanagementW.dto.RegisterEmployeeDTO;
import br.com.workmanagementW.dto.ViewEmployeeDTO;
import br.com.workmanagementW.dto.ViewEmployeeWithPresenceDTO;
import br.com.workmanagementW.dto.ViewEmployeeWithValueWorkedDTO;
import br.com.workmanagementW.entity.Employee;
import br.com.workmanagementW.repository.RepositoryEmployee;
import br.com.workmanagementW.repository.RepositoryPresence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeService {

    @Autowired
    private RepositoryEmployee repositoryEmployee;

    @Autowired
    private RepositoryPresence repositoryPresence;

    public ViewEmployeeDTO createEmployee(RegisterEmployeeDTO dto) {

        var employee = new Employee(dto);
        repositoryEmployee.save(employee);

        return new ViewEmployeeDTO(employee);

    }

    public Page<ViewEmployeeDTO> findAllEmployee(Pageable page) {
        return repositoryEmployee.findAll(page).map(ViewEmployeeDTO::new);
    }

    public Page<ViewEmployeeWithPresenceDTO> getEmployeeValueReceive(Pageable pageable, LocalDate date) {

        var dateNow = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        var dateNext = dateNow.plusMonths(1);

        return repositoryEmployee.findAll(pageable).map(employee -> {

            var presence = repositoryPresence.findAllPresencesWithEmployeeIdByDate(employee.getId(), dateNow, dateNext);

            return new ViewEmployeeWithPresenceDTO(employee, presence);
        });
    }

    public void deleteEmployee(Long id) {
        repositoryEmployee.deleteById(id);
    }


}
