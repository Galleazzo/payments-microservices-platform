package com.br.ms_save_payments.repository;

import com.br.ms_save_payments.domain.entity.Payment;
import com.br.ms_save_payments.domain.entity.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    boolean existsByOrderIdAndStatus(UUID uuid, PaymentStatus paymentStatus);
}