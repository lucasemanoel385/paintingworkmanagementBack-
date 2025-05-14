package br.com.workmanagementW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterRevenueDTO(
        Long id,
        String descriptionRevenue,
        BigDecimal valueEntry,
        LocalDate dateRevenue,
        Long workId
) {
}
