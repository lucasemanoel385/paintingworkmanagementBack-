package br.com.workmanagementW.dto;

import java.time.LocalDate;

public record PaymentAllEmployeeByWorkId(
        Long workId,
        String descriptionPayment,
        LocalDate startDate,
        LocalDate finalDate
) {
}
