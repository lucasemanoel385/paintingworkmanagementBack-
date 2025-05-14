package br.com.workmanagementW.entity;

import br.com.workmanagementW.dto.RegisterWorkDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameWork;
    private LocalDate startWork;
    private LocalDate finalWork;
    private BigDecimal grossValue;
    private BigDecimal liquidValue;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "work", fetch = FetchType.EAGER)
    @JsonManagedReference("presence-work")
    private List<Presence> presences;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "work", fetch = FetchType.LAZY)
    @JsonManagedReference("revenue-work")
    private List<Revenue> revenues;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "work", fetch = FetchType.LAZY)
    @JsonManagedReference("expense-work")
    private List<Expenses> expenses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "work", fetch = FetchType.LAZY)
    @JsonManagedReference("extraWorks-work")
    private List<ExtraWork> extraWorks;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "work", fetch = FetchType.LAZY)
    @JsonManagedReference("payment-work")
    private List<Payment> payments;

    public Work(RegisterWorkDTO dto) {
        this.nameWork = dto.nameWork();
        this.startWork = dto.startWork();
        this.finalWork = dto.finalWork();
        this.grossValue = dto.grossValue();
        this.liquidValue = dto.liquidValue() == null ? BigDecimal.valueOf(0) : dto.liquidValue();
        this.address = new Address(dto.city(),dto.street(), dto.district(), dto.number(), dto.cep());
    }

    public Work(Long workId) {
        this.id = workId;
    }

    public void addValueWork(BigDecimal value) {
        this.liquidValue = this.liquidValue.add(value);
    }

    public void subtractValueWork(BigDecimal value) {

        this.liquidValue = this.liquidValue.subtract(value);
    }

}
