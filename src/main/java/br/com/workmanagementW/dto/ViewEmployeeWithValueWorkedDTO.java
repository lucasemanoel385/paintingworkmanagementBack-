package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Employee;
import br.com.workmanagementW.entity.ExtraWork;
import br.com.workmanagementW.entity.Presence;
import br.com.workmanagementW.entity.Role;

import java.util.List;

public record ViewEmployeeWithValueWorkedDTO(
        Long id,
        String nameEmployee,
        List<Presence> presenceList,
        List<ExtraWork> extraWorkList,
        Role role
) {
    public ViewEmployeeWithValueWorkedDTO(Employee employee) {
        this(employee.getId(), employee.getNameEmployee(), employee.getPresence(), employee.getExtraworks(), employee.getRole());
    }
}
