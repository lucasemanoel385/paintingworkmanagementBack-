package br.com.workmanagementW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PaymentResumoDTO {
    String getNameEmployee();
    Long getEmployeeId();
    BigDecimal getValueReceive();
}
