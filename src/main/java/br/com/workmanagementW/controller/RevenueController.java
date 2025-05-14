package br.com.workmanagementW.controller;

import br.com.workmanagementW.dto.RegisterRevenueDTO;
import br.com.workmanagementW.dto.ViewRevenueDTO;
import br.com.workmanagementW.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @PostMapping("/register")
    public ResponseEntity createRevenue(@RequestBody RegisterRevenueDTO dto, UriComponentsBuilder uriBuilder){

        var revenue = revenueService.createRevenue(dto);
        URI uri = uriBuilder.path("/revenue/{id}").buildAndExpand(revenue.id()).toUri();
        return ResponseEntity.created(uri).body(revenue);

    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ViewRevenueDTO>> findAllRevenue() {
        return ResponseEntity.ok(revenueService.findAllRevenue());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<ViewRevenueDTO>> findRevenueByWorkId(@PathVariable Long id) {
        return ResponseEntity.ok(revenueService.findByWorkId(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRevenue(@PathVariable Long id) {
        revenueService.deleteRevenue(id);
    }
}
