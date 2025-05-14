package br.com.workmanagementW.service;

import br.com.workmanagementW.dto.PaymentAllEmployeeByWorkId;
import br.com.workmanagementW.dto.RegisterPaymentDTO;
import br.com.workmanagementW.dto.ViewPaymentDTO;
import br.com.workmanagementW.entity.Expenses;
import br.com.workmanagementW.entity.Payment;
import br.com.workmanagementW.repository.*;
import br.com.workmanagementW.validations.CheckIfEmployeePay;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PaymentService {

    @Autowired
    private RepositoryPayment repositoryPayment;

    @Autowired
    private RepositoryExpenses repositoryExpenses;

    @Autowired
    private RepositoryPresence repositoryPresence;

    @Autowired
    private RepositoryEmployee repositoryEmployee;

    @Autowired
    private RepositoryWork repositoryWork;

    @Autowired
    private CheckIfEmployeePay checkIfEmployeePay;

    @Transactional
    public void createPayment(RegisterPaymentDTO dto) {

        var employee = repositoryEmployee.findById(dto.employeeId()).orElseThrow();

        checkIfEmployeePay.validateEmployeePayment(dto.startDate(), dto.finalDate(), employee);

        var presence =
                repositoryPresence
                        .findAllPresencesWithEmployeeIdByDateToPayment(employee.getId(), dto.startDate(), dto.finalDate());

        presence.forEach(i -> {
            var valuePay = employee.getRole().getDailyPayment().multiply(BigDecimal.valueOf(i.getCountPresence()));
            var payment = new Payment(dto);
            payment.setValuePay(valuePay);
            repositoryPayment.save(payment);
        });
    }

    @Transactional
    public void createPaymentForAllEmployee(PaymentAllEmployeeByWorkId dto) {

        var presence =
                repositoryPresence
                        .findAllPresencesBetweenDateSpecify(dto.startDate(), dto.finalDate());

        presence.forEach(i -> {
            var employee = repositoryEmployee.findById(i.getEmployeeId()).orElseThrow();

            checkIfEmployeePay.validateEmployeePayment(dto.startDate(), dto.finalDate(), employee);

            var valuePay = employee.getRole().getDailyPayment().multiply(BigDecimal.valueOf(i.getCountPresence()));

            var payment = new Payment(dto.descriptionPayment(), valuePay, dto.startDate(), dto.finalDate(), employee.getId(), dto.workId());
            repositoryPayment.save(payment);

        });
    }

    @Transactional
    public void createPaymentForAllEmployeeByWorkId(PaymentAllEmployeeByWorkId dto) {

        var presence =
                repositoryPresence
                        .findAllPresencesBetweenDateSpecifyByWorkId(dto.workId(), dto.startDate(), dto.finalDate());

        presence.forEach(i -> {
            var employee = repositoryEmployee.findById(i.getEmployeeId()).orElseThrow();

            checkIfEmployeePay.validateEmployeePayment(dto.startDate(), dto.finalDate(), employee);

            var valuePay = employee.getRole().getDailyPayment().multiply(BigDecimal.valueOf(i.getCountPresence()));

            var payment = new Payment(dto.descriptionPayment(), valuePay, dto.startDate(), dto.finalDate(), employee.getId(), dto.workId());
            repositoryPayment.save(payment);

            //var expenses = new Expenses(dto.descriptionPayment() + i.getNameEmployee(), LocalDate.now(), valuePay, i.getWorkId());
            //var work = repositoryWork.findById(i.getWorkId()).get();
            //work.subtractValueWork(valuePay);
            //repositoryExpenses.save(expenses);
            //repositoryWork.save(work);
        });
    }

    public Page<ViewPaymentDTO> findAllPayment(Pageable pageable) {
        return repositoryPayment.findAll(pageable).map(ViewPaymentDTO::new);
    }

    public void deletePayment(Long id) {
        repositoryPayment.deleteById(id);
    }


}
