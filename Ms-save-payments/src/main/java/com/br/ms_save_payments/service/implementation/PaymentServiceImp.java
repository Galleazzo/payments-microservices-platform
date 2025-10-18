package com.br.ms_save_payments.service.implementation;

import com.br.ms_save_payments.domain.entity.Payment;
import com.br.ms_save_payments.domain.entity.dto.PaymentDTO;
import com.br.ms_save_payments.domain.entity.enums.PaymentStatus;
import com.br.ms_save_payments.exception.BusinessException;
import com.br.ms_save_payments.repository.PaymentRepository;
import com.br.ms_save_payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentServiceImp(@Autowired PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDTO create(PaymentDTO paymentDTO) {
        if (paymentDTO.amount() == null || paymentDTO.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O valor do pagamento deve ser maior que zero.");
        }

        if (paymentDTO.amount().compareTo(new BigDecimal("100000.00")) > 0) {
            throw new BusinessException("O valor do pagamento excede o limite permitido (R$ 100.000,00).");
        }

        if (paymentDTO.orderId() == null) {
            throw new BusinessException("O ID do pedido é obrigatório.");
        }

        boolean existsPending = paymentRepository.existsByOrderIdAndStatus(paymentDTO.orderId(), PaymentStatus.PENDING);
        if (existsPending) {
            throw new BusinessException("Já existe um pagamento pendente para este pedido.");
        }

        List<String> validMethods = List.of("CREDIT_CARD", "PIX", "BOLETO", "DEBIT_CARD");

        String method = paymentDTO.paymentMethod();
        if (method == null || !validMethods.contains(method.trim().toUpperCase())) {
            throw new BusinessException("Método de pagamento inválido. Use: CREDIT_CARD, PIX, BOLETO ou DEBIT_CARD.");
        }

        if (paymentDTO.description() != null && paymentDTO.description().length() > 255) {
            throw new BusinessException("Descrição muito longa (máximo 255 caracteres).");
        }

        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.orderId());
        payment.setAmount(paymentDTO.amount());
        payment.setPaymentMethod(paymentDTO.paymentMethod().toUpperCase());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setTransactionDate(LocalDateTime.now());
        payment.setDescription(paymentDTO.description());

        Payment saved = paymentRepository.save(payment);

        return new PaymentDTO(
                saved.getPaymentId(),
                saved.getOrderId(),
                saved.getAmount(),
                saved.getStatus(),
                saved.getPaymentMethod(),
                saved.getTransactionDate(),
                saved.getConfirmationDate(),
                saved.getDescription()
        );
    }
}
