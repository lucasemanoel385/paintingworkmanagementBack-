package br.com.workmanagementW.entity;

import br.com.workmanagementW.dto.RegisterExpensesDTO;
import br.com.workmanagementW.dto.RegisterExtraWorkDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descriptionExpenses;
    private BigDecimal valueExpenses;
    private LocalDate dateExpense;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    @JsonBackReference("expense-work")
    private Work work;

    public Expenses(RegisterExpensesDTO dto) {
        this.descriptionExpenses = dto.descriptionExpenses();
        this.valueExpenses = dto.valueExpenses();
        this.dateExpense = dto.dateExpense();
    }

    public Expenses(String descriptionExpenses, LocalDate dateNow, BigDecimal valuePay, Long workId) {
        this.descriptionExpenses = descriptionExpenses;
        this.valueExpenses = valuePay;
        this.dateExpense = dateNow;
        this.work = new Work(workId);
    }

    public Expenses(RegisterExtraWorkDTO dto) {
        this.descriptionExpenses = dto.descriptionExtraWork();
        this.valueExpenses = dto.paymentExtraWork();
        this.dateExpense = dto.dateExtraWork();
        this.work = new Work(dto.workId());
    }
}
