package br.com.workmanagementW.repository;

import br.com.workmanagementW.dto.PaymentResumoDTO;
import br.com.workmanagementW.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RepositoryPayment extends JpaRepository<Payment, Long> {


    @Query("""
            SELECT COUNT(p) > 0
            FROM Payment p
            WHERE p.employee.id = :id
            AND p.finalDate >= :startDate
            AND p.startDate <= :finalDate
            """)
    Boolean checkIfEmployeePaid(LocalDate startDate, LocalDate finalDate, Long id);

    @Query("""
            SELECT p
            FROM Payment p
            WHERE p.finalDate >= :startDate
            AND p.startDate <= :finalDate
            """)
    List<Payment> findAllByDate(LocalDate startDate, LocalDate finalDate);

    @Query("""
            SELECT e.nameEmployee as nameEmployee, e.id as employeeId,
            SUM(p.valuePay) as valueReceive
            FROM Payment p
            JOIN p.employee e
            WHERE p.startDate >= :startDate
            AND p.finalDate <= :finalDate
            GROUP BY e.nameEmployee, e.id
            """)
    List<PaymentResumoDTO> findAllByDateAndPaymentEmployee(LocalDate startDate, LocalDate finalDate);
}
