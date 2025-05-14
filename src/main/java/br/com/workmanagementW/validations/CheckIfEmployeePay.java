package br.com.workmanagementW.validations;

import br.com.workmanagementW.entity.Employee;
import br.com.workmanagementW.repository.RepositoryPayment;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CheckIfEmployeePay {

    @Autowired
    private RepositoryPayment repositoryPayment;

    public void validateEmployeePayment(LocalDate startDate, LocalDate finalDate, Employee employee) {

        var payOrNot = repositoryPayment.checkIfEmployeePaid(startDate, finalDate, employee.getId());

        if (payOrNot) {
            throw new ValidationException("Funcionário pago.");
        }


    }
}
