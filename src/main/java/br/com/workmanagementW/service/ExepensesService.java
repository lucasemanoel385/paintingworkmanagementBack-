package br.com.workmanagementW.service;

import br.com.workmanagementW.dto.RegisterExpensesDTO;
import br.com.workmanagementW.dto.ViewExpensesDTO;
import br.com.workmanagementW.entity.Expenses;
import br.com.workmanagementW.repository.RepositoryEmployee;
import br.com.workmanagementW.repository.RepositoryExpenses;
import br.com.workmanagementW.repository.RepositoryPresence;
import br.com.workmanagementW.repository.RepositoryWork;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExepensesService {

    @Autowired
    private RepositoryExpenses repositoryExpenses;

    @Autowired
    private RepositoryPresence repositoryPresence;

    @Autowired
    private RepositoryEmployee repositoryEmployee;

    @Autowired
    private RepositoryWork repositoryWork;

    @Transactional
    public ViewExpensesDTO createExpenses(RegisterExpensesDTO dto) {
        var expenses = new Expenses(dto);


        if (dto.workId() != null) {
            var work = repositoryWork.findById(dto.workId()).get();
            //work.subtractValueWork(expenses.getValueExpenses());
            expenses.setWork(work);
        }

        repositoryExpenses.save(expenses);

        return new ViewExpensesDTO(expenses);
    }

    public Page<ViewExpensesDTO> findAllExpenses(Pageable pageable) {
        return repositoryExpenses.findAll(pageable).map(ViewExpensesDTO::new);
    }

    public List<ViewExpensesDTO> findExpensesByWorkId(Long workId) {
        return repositoryExpenses.findAllByWorkId(workId).stream().map(ViewExpensesDTO::new).toList();
    }

    /*public void payAllEmployee(LocalDate startDate, LocalDate finalDate) {

        var presence =
                repositoryPresence
                        .findAllPresencesBetweenDateSpecify(startDate, finalDate);

        presence.forEach(i -> {
            var employee = repositoryEmployee.findById(i.getEmployeeId()).orElseThrow();
            var valuePay = employee.getRole().getDailyPayment().multiply(BigDecimal.valueOf(i.getCountPresence()));
            System.out.println(valuePay + " " + i.getWorkId());
            var expenses = new Expenses("Pagamento pawra funcionario " + i.getNameEmployee(), valuePay, i.getWorkId());
            System.out.println(expenses.getWork().getId());
            repositoryExpenses.save(expenses);
        });

    }*/

    /*public void payEmployeeById(Long id, String descriptionExpenses, LocalDate startDate, LocalDate finalDate) {

        var employee = repositoryEmployee.findById(id).orElseThrow();

        var presence =
                repositoryPresence
                        .findAllPresencesWithEmployeeIdByDateToPayment(employee.getId(), startDate, finalDate);

        presence.forEach(i -> {
            var valuePay = employee.getRole().getDailyPayment().multiply(BigDecimal.valueOf(i.getCountPresence()));
            new Expenses(descriptionExpenses, valuePay, i.getWorkId());
        });

    }*/

    public void deleteExpenses(Long id) {

        //var expenses = repositoryExpenses.findById(id).orElseThrow();

        //expenses.getWork().addValueWork(expenses.getValueExpenses());

        repositoryExpenses.deleteById(id);

    }
}
