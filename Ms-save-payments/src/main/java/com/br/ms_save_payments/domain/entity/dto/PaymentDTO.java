package com.br.ms_save_payments.domain.entity.dto;

import com.br.ms_save_payments.domain.entity.enums.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentDTO(
        UUID paymentId,
        UUID orderId,
        BigDecimal amount,
        PaymentStatus status,
        String paymentMethod,
        LocalDateTime transactionDate,
        LocalDateTime confirmationDate,
        String description
) {}
