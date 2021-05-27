package com.novapile.paymentservice.VO;

import javax.persistence.Entity;

import com.novapile.paymentservice.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Payment payment;
    private User user;
}
