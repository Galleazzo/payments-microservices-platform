package com.br.ms_save_payments.controller;

import com.br.ms_save_payments.domain.entity.dto.PaymentDTO;
import com.br.ms_save_payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO paymentDTO) {
        return new ResponseEntity<>(this.paymentService.create(paymentDTO), HttpStatus.CREATED);
    }
}
