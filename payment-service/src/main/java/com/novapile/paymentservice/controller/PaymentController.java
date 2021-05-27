package com.novapile.paymentservice.controller;

import java.util.List;

import com.novapile.paymentservice.VO.ResponseTemplateVO;
import com.novapile.paymentservice.entity.Payment;
import com.novapile.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public Payment savePayment(@RequestBody Payment payment){
        log.info("PaymentController - Payment Saved {}",payment);
        return paymentService.savePayment(payment);
    }

    @GetMapping("/")
    public List getAllPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping("/user/{id}")
    public List getAllPaymentsByUserId(@PathVariable("id") Long userId){
        return paymentService.getAllPaymentsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Payment findPaymentById(@PathVariable("id") Long paymentId){
      return paymentService.findPaymentByPaymentId(paymentId);
    }

    @GetMapping("/{id}/user/")
    public ResponseTemplateVO getPaymentWithUser(@PathVariable("id") Long paymentId) {
        return paymentService.getPaymentWithUser(paymentId);
    }

}
