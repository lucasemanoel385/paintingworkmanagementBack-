package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.ExtraWork;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ViewExtraWorkDTO(
        Long id,
        String descriptionExtraWork,
        LocalDate dateExtraWork,
        BigDecimal paymentExtraWork,
        Long employeeId,
        String employeeName,
        Long workId,
        String workName
) {
    public ViewExtraWorkDTO(ExtraWork extraWork){
        this(extraWork.getId(),extraWork.getDescriptionExtraWork(),
                extraWork.getDateExtraWork(),extraWork.getPaymentExtraWork(),
                extraWork.getEmployee().getId(), extraWork.getEmployee().getNameEmployee(),
                extraWork.getWork().getId(),
                extraWork.getWork().getNameWork());
    }
}
