package br.com.workmanagementW.service;

import br.com.workmanagementW.dto.RegisterWorkDTO;
import br.com.workmanagementW.dto.ViewWorkWithPresenceByDateDTO;
import br.com.workmanagementW.entity.Work;
import br.com.workmanagementW.repository.RepositoryPresence;
import br.com.workmanagementW.repository.RepositoryWork;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WorkService {

    @Autowired
    private RepositoryWork repositoryWork;

    @Autowired
    private RepositoryPresence repositoryPresence;

    public RegisterWorkDTO createWork(RegisterWorkDTO dto) {
        Work work = new Work(dto);
        repositoryWork.save(work);
        return new RegisterWorkDTO(work);
    }

    public void updateWork(RegisterWorkDTO dto) {

        Work work = new Work(dto);
        work.setId(dto.id());
        repositoryWork.save(work);

    }

    public Page<RegisterWorkDTO> findAllWorks(Pageable pageable, String nameWork) {

        return (nameWork == null) ?
                repositoryWork.findAll(pageable).map(RegisterWorkDTO::new)
                :
                repositoryWork.findAllByNameWorkContainingIgnoreCase(nameWork, pageable).map(RegisterWorkDTO::new);

    }

    public ViewWorkWithPresenceByDateDTO findWorkById(Long id) {

        var work = repositoryWork.findById(id).orElseThrow(EntityNotFoundException::new);

        var presenceInWork = repositoryPresence.findAllPresencesWithWorkIdByDate(id, LocalDate.now());

        return new ViewWorkWithPresenceByDateDTO(work, presenceInWork);

    }

    public void deleteWork(Long id) {
        repositoryWork.deleteById(id);
    }



}
