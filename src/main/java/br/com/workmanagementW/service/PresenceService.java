package br.com.workmanagementW.service;

import br.com.workmanagementW.dto.*;
import br.com.workmanagementW.entity.Presence;
import br.com.workmanagementW.enunm.PresenceOrFalse;
import br.com.workmanagementW.repository.RepositoryPresence;
import br.com.workmanagementW.repository.RepositoryWork;
import br.com.workmanagementW.validations.CheckIfEmployeeHasPresenceOnDay;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PresenceService {

    @Autowired
    private RepositoryPresence repositoryPresence;

    @Autowired
    private RepositoryWork repositoryWork;

    @Autowired
    private CheckIfEmployeeHasPresenceOnDay checkIfEmployeeHasPresenceOnDay;

    @Transactional
    public ViewPresenceDTO createPresence(RegisterPresenceDTO dto) {

        checkIfEmployeeHasPresenceOnDay.validate(dto);

        var presence = new Presence(dto);
        repositoryPresence.save(presence);
        return new ViewPresenceDTO(presence);
    }

    @Transactional
    public void createPresenceForAll(RegisterDiversPresenceDTO dto) {

        repositoryWork.findById(dto.workId()).orElseThrow();

        dto.employeeId().forEach(i -> {
            var dtoPresence = new RegisterPresenceDTO(dto, i);
            if (checkIfEmployeeHasPresenceOnDay.validate(dtoPresence)) {
                return;
            }
            //checkIfEmployeeHasPresenceOnDay.validate(dtoPresence);
            var presence = new Presence(dtoPresence, PresenceOrFalse.PRESENCE);
            repositoryPresence.save(presence);
        });
    }

    public Page<ViewPresenceDTO> findAllPresence(Pageable pageable) {

        return repositoryPresence.findAll(pageable).map(ViewPresenceDTO::new);
    }

    public List<ViewEmployeeIdAndNameDTO> findAllPresenceByWorkIdAndDateSpecify(Long workId, LocalDate dateSpecify) {
        return repositoryPresence.findAllByWorkIdAndDayPresence(workId, dateSpecify).stream().map(ViewEmployeeIdAndNameDTO::new).toList();
    }

    @Transactional
    public void deletePresence(Long id) {
        System.out.println(id);
        repositoryPresence.deleteById(id);
    }


}
