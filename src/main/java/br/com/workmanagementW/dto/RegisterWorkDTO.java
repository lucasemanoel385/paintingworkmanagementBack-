package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record RegisterWorkDTO(
        Long id,
        String nameWork,
        LocalDate startWork,
        LocalDate finalWork,
        BigDecimal grossValue,
        BigDecimal liquidValue,
        String city,
        String street,
        String district,
        String number,
        String cep,
        List<Presence> presencesList,
        List<Revenue> revenuesList,
        List<Expenses> expensesList
) {
    public RegisterWorkDTO(Work work){
        this(work.getId(), work.getNameWork(), work.getStartWork(), work.getFinalWork(), work.getGrossValue(), work.getLiquidValue(),
                work.getAddress().getCity(),
                work.getAddress().getStreet(),
                work.getAddress().getDistrict(),
                work.getAddress().getNumber(),
                work.getAddress().getCep(),
                work.getPresences(), work.getRevenues(), work.getExpenses());
    }
}
