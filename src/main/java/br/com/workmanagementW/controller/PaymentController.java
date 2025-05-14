package br.com.workmanagementW.controller;

import br.com.workmanagementW.dto.PaymentAllEmployeeByWorkId;
import br.com.workmanagementW.dto.RegisterPaymentDTO;
import br.com.workmanagementW.dto.RegisterRoleDTO;
import br.com.workmanagementW.dto.ViewPaymentDTO;
import br.com.workmanagementW.service.PaymentService;
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

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity createPayment(@RequestBody RegisterPaymentDTO dto){

        paymentService.createPayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/payment-all-employee")
    public ResponseEntity createPaymentForAllEmployee(@RequestBody PaymentAllEmployeeByWorkId data){

        paymentService.createPaymentForAllEmployeeByWorkId(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ViewPaymentDTO>> findAllPayment(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(paymentService.findAllPayment(pageable));
    }

    @DeleteMapping("/delete/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }

    //@PatchMapping
    //public ResponseEntity addEmployeeInWork(@RequestBody List<RegisterEmployeeInWorkDTO> dto) {
    //}

}
