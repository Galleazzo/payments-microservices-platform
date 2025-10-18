package com.br.ms_save_payments.service.implementation;

import com.br.ms_save_payments.domain.entity.dto.PaymentDTO;
import com.br.ms_save_payments.repository.PaymentRepository;
import com.br.ms_save_payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService {

    
    public PaymentServiceImp(@Autowired PaymentRepository paymentRepository) {
    }

    @Override
    public PaymentDTO create(PaymentDTO paymentDTO) {
        return null;
    }
}
