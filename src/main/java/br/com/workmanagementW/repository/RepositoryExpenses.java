package br.com.workmanagementW.repository;

import br.com.workmanagementW.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RepositoryExpenses extends JpaRepository<Expenses, Long> {
    List<Expenses> findAllByWorkId(Long workId);

    @Query("""
            SELECT e
            FROM Expenses e
            WHERE e.dateExpense >= :startDate
            AND e.dateExpense <= :finalDate
            """)
    List<Expenses> findAllByDate(LocalDate startDate, LocalDate finalDate);
}
