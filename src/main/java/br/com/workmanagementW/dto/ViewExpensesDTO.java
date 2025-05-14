package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Expenses;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ViewExpensesDTO(
        Long id,
        String descriptionExpenses,
        BigDecimal valueExpenses,
        LocalDate dateExpense,
        Long workId,
        String workName
) {
    public ViewExpensesDTO(Expenses expenses) {
        this(expenses.getId(), expenses.getDescriptionExpenses(), expenses.getValueExpenses(),
                expenses.getDateExpense(),
                expenses.getWork() != null ? expenses.getWork().getId() : null,
                expenses.getWork() != null ? expenses.getWork().getNameWork() : null
        );
    }
}
