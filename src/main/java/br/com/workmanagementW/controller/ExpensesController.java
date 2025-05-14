package br.com.workmanagementW.controller;

import br.com.workmanagementW.dto.RegisterExpensesDTO;
import br.com.workmanagementW.dto.ViewExpensesDTO;
import br.com.workmanagementW.service.ExepensesService;
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
@RequestMapping("api/expense")
public class ExpensesController {

    @Autowired
    private ExepensesService expensesService;

    @PostMapping("/register")
    public ResponseEntity createExpenses(@RequestBody RegisterExpensesDTO dto, UriComponentsBuilder uriBuilder){

        var expenses = expensesService.createExpenses(dto);
        URI uri = uriBuilder.path("/expenses/{id}").buildAndExpand(expenses.id()).toUri();
        return ResponseEntity.created(uri).body(expenses);

    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<ViewExpensesDTO>> findAllExpenses(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(expensesService.findAllExpenses(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<ViewExpensesDTO>> findExpensesByWorkId(@PathVariable Long id) {
        return ResponseEntity.ok(expensesService.findExpensesByWorkId(id));
    }

    /*@PatchMapping("pay-employee")
    public ResponseEntity payAllEmployee(@RequestParam String descriptionExpenses, @RequestParam LocalDate startDate, @RequestParam LocalDate finalDate) {
        expensesService.payAllEmployee(startDate, finalDate);
        return ResponseEntity.ok().build();
    }*/

    /*@PatchMapping("pay-employee/{id}")
    public ResponseEntity payEmployeeById(@PathVariable Long id, @RequestParam String descriptionExpenses ,@RequestParam LocalDate startDate, @RequestParam LocalDate finalDate) {
        expensesService.payEmployeeById(id, descriptionExpenses,startDate, finalDate);
        return ResponseEntity.ok().build();
    }*/

    @DeleteMapping("/delete/{id}")
    public void deleteExpenses(@PathVariable Long id) {
        expensesService.deleteExpenses(id);


    }
}
