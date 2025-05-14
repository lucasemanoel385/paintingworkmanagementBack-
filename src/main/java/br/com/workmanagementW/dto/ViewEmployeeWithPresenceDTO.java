package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Employee;
import br.com.workmanagementW.entity.ExtraWork;
import br.com.workmanagementW.entity.Presence;
import br.com.workmanagementW.entity.Role;

import java.util.List;

public record ViewEmployeeWithPresenceDTO(
        Long id,
        String nameEmployee,
        List<Presence> presenceList,
        List<ExtraWork> extraWorkList,
        Role role
) {
    public ViewEmployeeWithPresenceDTO(Employee employee, List<Presence> presenceList) {
        this(employee.getId(), employee.getNameEmployee(), presenceList, employee.getExtraworks(), employee.getRole());
    }
}
