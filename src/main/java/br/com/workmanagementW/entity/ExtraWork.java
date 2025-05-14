package br.com.workmanagementW.entity;

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
@Table(name = "extrawork")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ExtraWork {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descriptionExtraWork;
    private LocalDate dateExtraWork;
    private BigDecimal paymentExtraWork;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference("extraWorks-employee")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "work_id")
    @JsonBackReference("extraWorks-work")
    private Work work;

    public ExtraWork(RegisterExtraWorkDTO dto) {
        this.descriptionExtraWork = dto.descriptionExtraWork();
        this.dateExtraWork = dto.dateExtraWork();
        this.paymentExtraWork = dto.paymentExtraWork();
        this.employee = new Employee(dto.id());
        this.work = new Work(dto.workId());
    }

    public ExtraWork(RegisterExtraWorkDTO dtoExtraWork, Long employeeId) {
        this.descriptionExtraWork = dtoExtraWork.descriptionExtraWork();
        this.dateExtraWork = dtoExtraWork.dateExtraWork();
        this.paymentExtraWork = dtoExtraWork.paymentExtraWork();
        this.employee = new Employee(employeeId);
        this.work = new Work(dtoExtraWork.workId());
    }
}
