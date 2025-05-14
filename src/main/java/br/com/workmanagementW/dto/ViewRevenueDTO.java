package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Revenue;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ViewRevenueDTO(
        Long id,
        String descriptionRevenue,
        BigDecimal valueEntry,
        LocalDate dateRevenue,
        Long workId,
        String workName
) {
    public ViewRevenueDTO(Revenue revenue){
        this(revenue.getId(), revenue.getDescriptionRevenue(), revenue.getValueEntry(), revenue.getDateRevenue(), revenue.getWork().getId(), revenue.getWork().getNameWork());
    }
}
