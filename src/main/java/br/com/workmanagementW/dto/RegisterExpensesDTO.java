package br.com.workmanagementW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterExpensesDTO(
        Long id,
        String descriptionExpenses,
        BigDecimal valueExpenses,
        LocalDate dateExpense,
        Long workId
) {
}
