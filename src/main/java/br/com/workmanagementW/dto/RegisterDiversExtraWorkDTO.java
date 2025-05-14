package br.com.workmanagementW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record RegisterDiversExtraWorkDTO(
        Long id,
        String descriptionExtraWork,
        LocalDate dateExtraWork,
        BigDecimal paymentExtraWork,
        List<Long> employeeId,
        Long workId
) {


}
