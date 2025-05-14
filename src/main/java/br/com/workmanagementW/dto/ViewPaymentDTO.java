package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Employee;
import br.com.workmanagementW.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ViewPaymentDTO(
        Long id,
        String descriptionPayment,
        BigDecimal valuePay,
        LocalDate startDate,
        LocalDate finalDate,
        Long employeeId,
        String nameEmployee
) {
    public ViewPaymentDTO(Payment payment) {
        this(payment.getId(), payment.getDescriptionPayment(), payment.getValuePay(), payment.getStartDate(), payment.getFinalDate(), payment.getEmployee().getId(), payment.getEmployee().getNameEmployee());
    }
}
