package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Presence;
import br.com.workmanagementW.enunm.PresenceOrFalse;

import java.time.LocalDate;

public record RegisterPresenceDTO(
        Long id,
        LocalDate dayPresence,
        PresenceOrFalse presenceDaily,
        Long workId,
        Long employeeId
) {
    public RegisterPresenceDTO(Presence presence){
        this(presence.getId(), presence.getDayPresence(), presence.getPresenceDaily(), presence.getWork().getId(), presence.getEmployee().getId());
    }

    public RegisterPresenceDTO(RegisterPresenceDTO presence) {
        this(presence.id(), presence.dayPresence(), presence.presenceDaily(), presence.workId(), presence.employeeId());
    }

    public RegisterPresenceDTO(RegisterDiversPresenceDTO dto, Long employee) {
        this(dto.id(), dto.dayPresence(), dto.presenceDaily(), dto.workId(), employee);
    }
}
