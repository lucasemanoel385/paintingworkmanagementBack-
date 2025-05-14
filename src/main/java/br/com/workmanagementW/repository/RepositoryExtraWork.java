package br.com.workmanagementW.repository;

import br.com.workmanagementW.dto.RegisterExtraWorkDTO;
import br.com.workmanagementW.entity.ExtraWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RepositoryExtraWork extends JpaRepository<ExtraWork, Long> {

    List<ExtraWork> findAllExtraWorkByWorkIdAndDateExtraWork(Long workId, LocalDate dateSpecify);

    @Query("""
            SELECT e 
            FROM ExtraWork e
            WHERE e.dateExtraWork >= :startDate
            AND e.dateExtraWork <= :finalDate
            """)
    List<ExtraWork> findAllByDate(LocalDate startDate, LocalDate finalDate);

    boolean existsByEmployeeIdAndDateExtraWork(Long aLong, LocalDate localDate);
}
