package com.novapile.paymentservice.service;

import java.util.List;

import com.novapile.paymentservice.VO.ResponseTemplateVO;
import com.novapile.paymentservice.VO.User;
import com.novapile.paymentservice.entity.Payment;
import com.novapile.paymentservice.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private RestTemplate restTemplate;

    String USER_SERVICE = "http://USER-SERVICE/users/";

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment findPaymentByPaymentId(Long id) {
        return paymentRepository.findByPaymentId(id);
    }

    public ResponseTemplateVO getPaymentWithUser(Long id) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Payment payment = paymentRepository.findByPaymentId(id);
        log.info("Payment {}",payment.toString());
        User user = restTemplate.getForObject(USER_SERVICE + payment.getUserId(),User.class);
        responseTemplateVO.setUser(user);
        responseTemplateVO.setPayment(payment);
        return responseTemplateVO;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List getAllPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }
}
