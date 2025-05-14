package br.com.workmanagementW.entity;

import br.com.workmanagementW.dto.RegisterPresenceDTO;
import br.com.workmanagementW.enunm.PresenceOrFalse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "presence")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dayPresence;
    @Enumerated(EnumType.STRING)
    private PresenceOrFalse presenceDaily;
    @ManyToOne
    @JoinColumn(name = "work_id")
    @JsonBackReference("presence-work")
    private Work work;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference("presence-employee")
    private Employee employee;

    public Presence(RegisterPresenceDTO dto) {

        this.dayPresence = dto.dayPresence();
        this.presenceDaily = dto.presenceDaily();
        this.work = new Work(dto.workId());
        this.employee = new Employee(dto.employeeId());
    }

    public Presence(RegisterPresenceDTO dto, PresenceOrFalse presenceOrFalse) {
        this.dayPresence = dto.dayPresence();
        this.presenceDaily = presenceOrFalse;
        this.work = new Work(dto.workId());
        this.employee = new Employee(dto.employeeId());
    }
}
