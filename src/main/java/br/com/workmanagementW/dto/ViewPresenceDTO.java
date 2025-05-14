package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Employee;
import br.com.workmanagementW.entity.Presence;
import br.com.workmanagementW.entity.Work;
import br.com.workmanagementW.enunm.PresenceOrFalse;

import java.time.LocalDate;

public record ViewPresenceDTO(
        Long id,
        LocalDate dayPresence,
        PresenceOrFalse presenceDaily,
        Long workId,
        String workName,
        Long employeeId,
        String employeeName
) {
    public ViewPresenceDTO(Presence presence) {
        this(presence.getId(), presence.getDayPresence(),
                presence.getPresenceDaily(),
                presence.getWork().getId(),
                presence.getWork().getNameWork(),
                presence.getEmployee().getId(),
                presence.getEmployee().getNameEmployee());
    }
}
