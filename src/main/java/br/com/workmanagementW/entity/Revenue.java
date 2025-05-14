package br.com.workmanagementW.entity;

import br.com.workmanagementW.dto.RegisterRevenueDTO;
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
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descriptionRevenue;
    private BigDecimal valueEntry;
    private LocalDate dateRevenue;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    @JsonBackReference("revenue-work")
    private Work work;

    public Revenue(RegisterRevenueDTO dto) {
        this.descriptionRevenue = dto.descriptionRevenue();
        this.valueEntry = dto.valueEntry();
        this.dateRevenue = dto.dateRevenue();
        this.work = new Work(dto.workId());
    }
}
