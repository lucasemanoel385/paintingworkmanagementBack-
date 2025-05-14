package br.com.workmanagementW.entity;

import br.com.workmanagementW.dto.RegisterEmployeeDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEmployee;
    @JsonManagedReference("presence-employee")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Presence> presence;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonManagedReference("extraWorks-employee")
    private List<ExtraWork> extraworks;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonManagedReference("payment-employee")
    private List<Payment> payments;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Employee(RegisterEmployeeDTO dto) {
        this.nameEmployee = dto.nameEmployee();
        this.role = new Role(dto.roleId());
    }

    public Employee(Long id) {
        this.id = id;
    }
}
