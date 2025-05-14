package br.com.workmanagementW.service;

import br.com.workmanagementW.dto.RegisterDiversExtraWorkDTO;
import br.com.workmanagementW.dto.RegisterExtraWorkDTO;
import br.com.workmanagementW.dto.ViewEmployeeIdAndNameDTO;
import br.com.workmanagementW.dto.ViewExtraWorkDTO;
import br.com.workmanagementW.entity.Expenses;
import br.com.workmanagementW.entity.ExtraWork;
import br.com.workmanagementW.repository.RepositoryExpenses;
import br.com.workmanagementW.repository.RepositoryExtraWork;
import br.com.workmanagementW.repository.RepositoryWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExtraWorkService {

    @Autowired
    private RepositoryExtraWork repositoryExtraWork;

    @Autowired
    private RepositoryExpenses repositoryExpenses;

    @Autowired
    private RepositoryWork repositoryWork;

    public RegisterExtraWorkDTO createExtraWork(RegisterExtraWorkDTO dto) {

        repositoryWork.findById(dto.workId()).orElseThrow();

        var extraWork = new ExtraWork(dto);
        repositoryExtraWork.save(extraWork);

        return new RegisterExtraWorkDTO(extraWork);
    }

    public void createExtraWorkForAll(RegisterDiversExtraWorkDTO dto) {

        var work = repositoryWork.findById(dto.workId()).orElseThrow();

        dto.employeeId().forEach(i -> {
            var dtoExtraWork = new RegisterExtraWorkDTO(dto, i);

            if (repositoryExtraWork.existsByEmployeeIdAndDateExtraWork(dtoExtraWork.employeeId(), dtoExtraWork.dateExtraWork())){
                return;
            }

            var extraWork = new ExtraWork(dtoExtraWork, i);
            repositoryExtraWork.save(extraWork);

            //var expenses = new Expenses(dtoExtraWork);
            //repositoryExpenses.save(expenses);

            //work.subtractValueWork(dto.paymentExtraWork());
            //repositoryWork.save(work);
        });
    }

    public Page<ViewExtraWorkDTO> findAllExtraWork(Pageable pageable) {
        return repositoryExtraWork.findAll(pageable).map(ViewExtraWorkDTO::new);
    }

    public List<ViewEmployeeIdAndNameDTO> findAllExtraWorkByWorkIdAndDateSpecify(Long workId, LocalDate dateSpecify) {
        return repositoryExtraWork.findAllExtraWorkByWorkIdAndDateExtraWork(workId, dateSpecify).stream().map(ViewEmployeeIdAndNameDTO::new).toList();
    }

    public void deleteExtraWork(Long id) {


        repositoryExtraWork.deleteById(id);
    }



}
