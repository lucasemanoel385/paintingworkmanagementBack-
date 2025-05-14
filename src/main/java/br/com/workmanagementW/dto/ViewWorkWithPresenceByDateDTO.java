package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ViewWorkWithPresenceByDateDTO(
        Long id,
        String nameWork,
        LocalDate startWork,
        LocalDate finalWork,
        BigDecimal grossValue,
        BigDecimal liquidValue,
        Address address,
        List<Presence> presencesList,
        List<Revenue> revenuesList,
        List<Expenses> expensesList
) {
    public ViewWorkWithPresenceByDateDTO(Work work, List<Presence> listPresence){
        this(work.getId(), work.getNameWork(), work.getStartWork(), work.getFinalWork(), work.getGrossValue(), work.getLiquidValue(), work.getAddress(),
                listPresence, work.getRevenues(), work.getExpenses());
    }
}
