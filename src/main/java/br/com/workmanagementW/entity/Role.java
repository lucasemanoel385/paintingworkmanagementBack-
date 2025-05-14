package br.com.workmanagementW.entity;

import br.com.workmanagementW.dto.RegisterRoleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameRole;
    private BigDecimal monthlyPayment;
    private BigDecimal dailyPayment;

    public Role(RegisterRoleDTO dto) {
        this.nameRole = dto.nameRole();
        this.monthlyPayment = dto.monthlyPayment();
        this.dailyPayment = dto.dailyPayment();
    }

    public Role(Long roleId) {
        this.id = roleId;
    }
}
