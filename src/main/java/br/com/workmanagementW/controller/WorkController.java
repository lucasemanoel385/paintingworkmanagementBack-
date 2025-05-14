package br.com.workmanagementW.controller;

import br.com.workmanagementW.dto.RegisterWorkDTO;
import br.com.workmanagementW.dto.ViewWorkWithPresenceByDateDTO;
import br.com.workmanagementW.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @PostMapping("/register")
    public ResponseEntity createWork(@RequestBody RegisterWorkDTO dto, UriComponentsBuilder uriBuilder){

        var work = workService.createWork(dto);
        URI uri = uriBuilder.path("/work/{id}").buildAndExpand(work.id()).toUri();
        return ResponseEntity.created(uri).body(work);

    }

    @PutMapping("/update")
    public ResponseEntity createWork(@RequestBody RegisterWorkDTO dto){

       workService.updateWork(dto);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<RegisterWorkDTO>> findAllWorks(@PageableDefault Pageable pageable, @RequestParam(required = false) String search) {

        return ResponseEntity.ok(workService.findAllWorks(pageable, search));
    }

    @GetMapping("get-id/{id}")
    public ResponseEntity<ViewWorkWithPresenceByDateDTO> findAllWorks(@PathVariable Long id) {
        return ResponseEntity.ok(workService.findWorkById(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWork(@PathVariable Long id) {
        workService.deleteWork(id);
    }

    //@PatchMapping
    //public ResponseEntity addEmployeeInWork(@RequestBody List<RegisterEmployeeInWorkDTO> dto) {
    //}

}
