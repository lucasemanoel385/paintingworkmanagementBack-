package br.com.workmanagementW.repository;

import br.com.workmanagementW.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RepositoryRevenue extends JpaRepository<Revenue, Long> {
    List<Revenue> findAllByWorkId(Long workId);

    @Query("""
            SELECT r
            FROM Revenue r
            WHERE r.dateRevenue >= :startDate
            AND r.dateRevenue <= :finalDate
            """)
    List<Revenue> findAllByDate(LocalDate startDate, LocalDate finalDate);
}
