package br.com.workmanagementW.repository;


import br.com.workmanagementW.dto.RegisterWorkDTO;
import br.com.workmanagementW.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.nio.channels.FileChannel;
import java.time.LocalDate;

public interface RepositoryWork extends JpaRepository<Work, Long> {

    @Query("""
            SELECT w
            FROM Work w
            JOIN Presence p ON p.work = w
            WHERE w.id = :id AND p.dayPresence = :dateNow
            """)
    RegisterWorkDTO findWorkByIdWithPresenceDay(@Param("id") Long id, @Param("dateNow") LocalDate dateNow);

    Page<Work> findAllByNameWorkContainingIgnoreCase(String nameWork, Pageable pageable);
}
