package br.com.workmanagementW.service;

import br.com.workmanagementW.dto.RegisterRevenueDTO;
import br.com.workmanagementW.dto.ViewRevenueDTO;
import br.com.workmanagementW.entity.Revenue;
import br.com.workmanagementW.repository.RepositoryRevenue;
import br.com.workmanagementW.repository.RepositoryWork;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueService {
    
    @Autowired
    private RepositoryRevenue repositoryRevenue;

    @Autowired
    private RepositoryWork repositoryWork;

    @Transactional
    public ViewRevenueDTO createRevenue(RegisterRevenueDTO dto) {
        var revenue = new Revenue(dto);
        repositoryRevenue.save(revenue);

        return new ViewRevenueDTO(revenue);

    }

    public List<ViewRevenueDTO> findAllRevenue() {
        return repositoryRevenue.findAll().stream().map(ViewRevenueDTO::new).toList();
    }

    public List<ViewRevenueDTO> findByWorkId(Long workId) {
        return repositoryRevenue.findAllByWorkId(workId).stream().map(ViewRevenueDTO::new).toList();
    }

    public void deleteRevenue(Long id) {

        repositoryRevenue.deleteById(id);
    }
}
