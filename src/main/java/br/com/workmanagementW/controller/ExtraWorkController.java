package br.com.workmanagementW.controller;

import br.com.workmanagementW.dto.*;
import br.com.workmanagementW.service.ExtraWorkService;
import br.com.workmanagementW.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/extrawork")
public class ExtraWorkController {

    @Autowired
    private ExtraWorkService extraWorkService;

    @PostMapping("/register")
    public ResponseEntity createExtraWork(@RequestBody RegisterExtraWorkDTO dto, UriComponentsBuilder uriBuilder){

        var extraWork = extraWorkService.createExtraWork(dto);
        URI uri = uriBuilder.path("/extrawork/{id}").buildAndExpand(extraWork.id()).toUri();
        return ResponseEntity.created(uri).body(extraWork);

    }

    @PostMapping("/register/extra-work-for-all")
    public ResponseEntity createExtraWorkForAll(@RequestBody RegisterDiversExtraWorkDTO dto){

       extraWorkService.createExtraWorkForAll(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<Page<ViewExtraWorkDTO>> findAllExtraWork(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(extraWorkService.findAllExtraWork(pageable));
    }

    @GetMapping("/get-extra-work-date")
    public ResponseEntity<List<ViewEmployeeIdAndNameDTO>> findAllExtraWork(@RequestParam Long workId, @RequestParam LocalDate dateSpecify) {
        return ResponseEntity.ok(extraWorkService.findAllExtraWorkByWorkIdAndDateSpecify(workId, dateSpecify));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExtraWork(@PathVariable Long id) {
        extraWorkService.deleteExtraWork(id);
    }

    //@PatchMapping
    //public ResponseEntity addEmployeeInWork(@RequestBody List<RegisterEmployeeInWorkDTO> dto) {
    //}

}
