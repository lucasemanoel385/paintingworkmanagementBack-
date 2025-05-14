package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Role;

import java.math.BigDecimal;

public record RegisterRoleDTO(
        Long id,
        String nameRole,
        BigDecimal monthlyPayment,
        BigDecimal dailyPayment
) {
    public RegisterRoleDTO(Role role) {
        this(role.getId(), role.getNameRole(), role.getMonthlyPayment(), role.getDailyPayment());
    }
}
