package br.com.workmanagementW.controller;

import br.com.workmanagementW.dto.RegisterEmployeeDTO;
import br.com.workmanagementW.dto.ViewEmployeeDTO;
import br.com.workmanagementW.dto.ViewEmployeeWithPresenceDTO;
import br.com.workmanagementW.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity createEmployee(@RequestBody RegisterEmployeeDTO dto, UriComponentsBuilder uriBuilder){

        var employee = employeeService.createEmployee(dto);
        URI uri = uriBuilder.path("/employee/{id}").buildAndExpand(employee.employeeId()).toUri();
        return ResponseEntity.created(uri).body(employee);

    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<ViewEmployeeDTO>> findAllEmployee(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(employeeService.findAllEmployee(pageable));
    }

    @GetMapping("value-receive/{date}")
    public ResponseEntity<Page<ViewEmployeeWithPresenceDTO>> getEmployeeValueReceive(@PageableDefault Pageable pageable, @PathVariable LocalDate date) {
        return ResponseEntity.ok(employeeService.getEmployeeValueReceive(pageable,date));
    }

    @DeleteMapping("delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
