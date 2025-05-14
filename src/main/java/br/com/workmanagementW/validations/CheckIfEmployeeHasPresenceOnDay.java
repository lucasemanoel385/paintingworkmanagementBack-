package br.com.workmanagementW.validations;

import br.com.workmanagementW.dto.RegisterPresenceDTO;
import br.com.workmanagementW.repository.RepositoryPresence;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CheckIfEmployeeHasPresenceOnDay {

    @Autowired
    private RepositoryPresence repositoryPresence;


    public boolean validate(RegisterPresenceDTO dto) {

        var countPresence = repositoryPresence.countPresenceOnDay(dto.employeeId(), dto.dayPresence());

        //if (countPresence > 0) {
        //    throw new ValidationException("Já marcou presença hoje.");
        //}
        return countPresence > 0;
    }
}
