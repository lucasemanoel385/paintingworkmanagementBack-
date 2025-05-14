package br.com.workmanagementW.controller;

import br.com.workmanagementW.dto.PaymentResumoDTO;
import br.com.workmanagementW.dto.ViewAllReportDTO;
import br.com.workmanagementW.dto.ViewExtraWorkDTO;
import br.com.workmanagementW.dto.ViewReportByWorkIdDTO;
import br.com.workmanagementW.entity.Expenses;
import br.com.workmanagementW.entity.ExtraWork;
import br.com.workmanagementW.entity.Payment;
import br.com.workmanagementW.entity.Revenue;
import br.com.workmanagementW.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/report")
public class ReportController {

    @Autowired
    private RepositoryWork repositoryWork;

    @Autowired
    private RepositoryExpenses repositoryExpenses;

    @Autowired
    private RepositoryRevenue repositoryRevenue;

    @Autowired
    private RepositoryPayment repositoryPayment;

    @Autowired
    private RepositoryExtraWork repositoryExtraWork;

    @GetMapping("/{id}")
    public ResponseEntity getReportByWorkId(@PathVariable Long id) {

        var work = repositoryWork.findById(id).orElseThrow();

        var expense = work.getExpenses().stream().map(Expenses::getValueExpenses).reduce(BigDecimal.ZERO, BigDecimal::add);

        var valueGross = work.getRevenues().stream().map(Revenue::getValueEntry).reduce(BigDecimal.ZERO, BigDecimal::add);

        var payment = work.getPayments().stream().map(Payment::getValuePay).reduce(BigDecimal.ZERO, BigDecimal::add);

        var extraWork = work.getExtraWorks().stream().map(ExtraWork::getPaymentExtraWork).reduce(BigDecimal.ZERO, BigDecimal::add);

        var valueLiquid = valueGross.subtract(expense).subtract(payment).subtract(extraWork);

        return ResponseEntity.ok(new ViewReportByWorkIdDTO(expense, valueGross, payment, extraWork, valueLiquid));
    }

    @GetMapping("/get-all")
    public ResponseEntity getReportAllWork(@RequestParam LocalDate startDate, @RequestParam LocalDate finalDate) {

        var extraWork = repositoryExtraWork.findAllByDate(startDate, finalDate).stream().map(ViewExtraWorkDTO::new).toList();
        var totalExtraWork = extraWork.stream().map(ViewExtraWorkDTO::paymentExtraWork).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        var expenses = repositoryExpenses.findAllByDate(startDate, finalDate);
        var totalExpense = expenses.stream().map(Expenses::getValueExpenses).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        var revenue = repositoryRevenue.findAllByDate(startDate, finalDate);
        var totalRevenue = revenue.stream().map(Revenue::getValueEntry).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        var payment = repositoryPayment.findAllByDate(startDate, finalDate);
        var totalPayment = payment.stream().map(Payment::getValuePay).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        List<PaymentResumoDTO> paymentEmployee = repositoryPayment.findAllByDateAndPaymentEmployee(startDate, finalDate)
                .stream()
                .map(i -> {
                    // Soma todos os valores extra para o mesmo employeeId
                    BigDecimal valorExtraTotal = extraWork.stream()
                            .filter(e -> e.employeeId().equals(i.getEmployeeId()))
                            .map(ViewExtraWorkDTO::paymentExtraWork)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    // Retorna o DTO somado
                    return new PaymentResumoDTO() {
                        @Override
                        public String getNameEmployee() {
                            return i.getNameEmployee();
                        }

                        @Override
                        public Long getEmployeeId() {
                            return i.getEmployeeId();
                        }

                        @Override
                        public BigDecimal getValueReceive() {
                            return i.getValueReceive().add(valorExtraTotal);
                        }
                    };
                })
                .collect(Collectors.toList());


        var totalPaymentEmployee = paymentEmployee.stream().map(PaymentResumoDTO::getValueReceive).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        var totalLiquid = totalRevenue.subtract(totalExtraWork).subtract(totalExpense).subtract(totalPayment);

        return ResponseEntity.ok(new ViewAllReportDTO(extraWork, expenses, revenue, payment, paymentEmployee,
                totalExtraWork, totalRevenue, totalExpense, totalPayment, totalPaymentEmployee, totalLiquid));
    }
}
