package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.ExtraWork;
import br.com.workmanagementW.entity.Presence;

public record ViewEmployeeIdAndNameDTO(
        Long id,
        Long employeeId,
        String nameEmployee
) {
    public ViewEmployeeIdAndNameDTO(ExtraWork extraWork){
        this(extraWork.getId() ,extraWork.getEmployee().getId(), extraWork.getEmployee().getNameEmployee());
    }

    public ViewEmployeeIdAndNameDTO(Presence presence){
        this(presence.getId(),presence.getEmployee().getId(), presence.getEmployee().getNameEmployee());
    }
}
