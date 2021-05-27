package com.novapile.paymentservice.repository;

import java.util.List;

import com.novapile.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByPaymentId(Long paymentId);

    List findByUserId(Long userId);
}
