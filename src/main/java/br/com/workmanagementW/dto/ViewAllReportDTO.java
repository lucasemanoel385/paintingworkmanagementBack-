package br.com.workmanagementW.dto;

import br.com.workmanagementW.entity.Expenses;
import br.com.workmanagementW.entity.Payment;
import br.com.workmanagementW.entity.Revenue;

import java.math.BigDecimal;
import java.util.List;

public record ViewAllReportDTO(
        List<ViewExtraWorkDTO> extraWorkList,
        List<Expenses> expensesList,
        List<Revenue> revenueList,
        List<Payment> paymentList,
        List<PaymentResumoDTO> paymentEmployeeList,
        BigDecimal totalExtraWork,
        BigDecimal totalRevenue,
        BigDecimal totalExpense,
        BigDecimal totalPayment,
        BigDecimal totalPaymentEmployee,
        BigDecimal totalLiquid
) {
}
