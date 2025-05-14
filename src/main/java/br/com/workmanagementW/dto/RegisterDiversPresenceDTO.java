package br.com.workmanagementW.dto;

import br.com.workmanagementW.enunm.PresenceOrFalse;

import java.time.LocalDate;
import java.util.List;

public record RegisterDiversPresenceDTO(
        Long id,
        LocalDate dayPresence,
        PresenceOrFalse presenceDaily,
        Long workId,
        List<Long> employeeId
) {

}
