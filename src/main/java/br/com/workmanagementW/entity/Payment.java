package br.com.workmanagementW.entity;

import br.com.workmanagementW.dto.RegisterPaymentDTO;
import br.com.workmanagementW.dto.RegisterRoleDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descriptionPayment;
    private BigDecimal valuePay;
    private LocalDate startDate;
    private LocalDate finalDate;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference("payment-employee")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "work_id")
    @JsonBackReference("payment-work")
    private Work work;

    public Payment(RegisterPaymentDTO dto) {
        this.descriptionPayment = dto.descriptionPayment();
        this.startDate = dto.startDate();
        this.finalDate = dto.finalDate();
        this.employee = new Employee(dto.employeeId());
    }

    public Payment(String descriptionExpenses, BigDecimal valuePay, LocalDate startDate, LocalDate finalDate, Long employeeId, Long workId) {
        this.descriptionPayment = descriptionExpenses;
        this.valuePay = valuePay;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.employee = new Employee(employeeId);
        this.work = new Work(workId);
    }
}
