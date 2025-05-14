package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterPaymentDTO(
        Long id,
        String descriptionPayment,
        BigDecimal valuePay,
        LocalDate startDate,
        LocalDate finalDate,
        Long employeeId
) {
    public RegisterPaymentDTO(Payment payment) {
        this(payment.getId(), payment.getDescriptionPayment(), payment.getValuePay(), payment.getStartDate(), payment.getFinalDate(), payment.getId());
    }
}
