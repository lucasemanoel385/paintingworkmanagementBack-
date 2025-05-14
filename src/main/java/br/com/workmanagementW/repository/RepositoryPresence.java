package br.com.workmanagementW.repository;

import br.com.workmanagementW.dto.GetWorkIdAndCountPresence;
import br.com.workmanagementW.dto.ViewPresenceDTO;
import br.com.workmanagementW.entity.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RepositoryPresence extends JpaRepository<Presence, Long> {


    @Query("""
            SELECT p
            FROM Presence p
            JOIN FETCH p.work w
            WHERE w.id = :id AND p.dayPresence = :dateNow
            """)
    List<Presence> findAllPresencesWithWorkIdByDate(Long id, LocalDate dateNow);

    @Query("""
            SELECT p
            FROM Presence p
            JOIN FETCH p.employee e
            WHERE e.id = :id 
            AND p.dayPresence >= :dateNow 
            AND p.dayPresence < :dateEnd
            """)
    List<Presence> findAllPresencesWithEmployeeIdByDate(Long id, LocalDate dateNow, LocalDate dateEnd);

    @Query("""
            SELECT p.work.id as workId, e.nameEmployee as nameEmployee, count(p) as countPresence
            FROM Presence p
            JOIN p.employee e
            WHERE e.id = :id
            AND p.dayPresence >= :dateNow
            AND p.dayPresence <= :dateEnd
            AND p.presenceDaily = PRESENCE
            GROUP BY p.work.id
            """)
    List<GetWorkIdAndCountPresence> findAllPresencesWithEmployeeIdByDateToPayment(Long id, LocalDate dateNow, LocalDate dateEnd);

    @Query("""
            SELECT p.employee.id as employeeId, e.nameEmployee as nameEmployee ,count(p) as countPresence, p.work.id as workId
            FROM Presence p
            JOIN p.employee e
            WHERE e.id = p.employee.id
            AND p.dayPresence >= :dateNow
            AND p.dayPresence <= :dateEnd
            AND p.presenceDaily = PRESENCE
            GROUP BY p.employee.id, e.nameEmployee, p.work.id
            """)
    List<GetWorkIdAndCountPresence> findAllPresencesBetweenDateSpecify(LocalDate dateNow, LocalDate dateEnd);

    @Query("""
            SELECT p.employee.id as employeeId, e.nameEmployee as nameEmployee ,count(p) as countPresence, p.work.id as workId
            FROM Presence p
            JOIN p.employee e
            WHERE e.id = p.employee.id
            AND p.work.id = :workId
            AND p.dayPresence >= :dateNow
            AND p.dayPresence <= :dateEnd
            AND p.presenceDaily = PRESENCE
            GROUP BY p.employee.id, e.nameEmployee, p.work.id
            """)
    List<GetWorkIdAndCountPresence> findAllPresencesBetweenDateSpecifyByWorkId(Long workId, LocalDate dateNow, LocalDate dateEnd);

    @Query("""
            SELECT count(p)
            FROM Presence p
            WHERE p.employee.id = :employeeId
            AND
            p.dayPresence = :dateNow
            """)
    int countPresenceOnDay(Long employeeId, LocalDate dateNow);

    List<Presence> findAllByWorkIdAndDayPresence(Long workId, LocalDate dateSpecify);
}
