package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.ExtraWork;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterExtraWorkDTO(
        Long id,
        String descriptionExtraWork,
        LocalDate dateExtraWork,
        BigDecimal paymentExtraWork,
        Long employeeId,
        Long workId
) {
    public RegisterExtraWorkDTO(ExtraWork extraWork){
        this(extraWork.getId(),extraWork.getDescriptionExtraWork(),
                extraWork.getDateExtraWork(),extraWork.getPaymentExtraWork(),
                extraWork.getEmployee().getId(), extraWork.getWork().getId());
    }

    public RegisterExtraWorkDTO(RegisterExtraWorkDTO i) {
        this(i.id(), i.descriptionExtraWork(), i.dateExtraWork(), i.paymentExtraWork(), i.employeeId(), i.workId());
    }

    public RegisterExtraWorkDTO(RegisterDiversExtraWorkDTO dto, Long i) {
        this(dto.id(),dto.descriptionExtraWork(),
                dto.dateExtraWork(),dto.paymentExtraWork(),
                i, dto.workId());
    }
}
