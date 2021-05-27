package com.novapile.gatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallbackMethod")
    public String userServiceFallbackMethod(){
        return "USER-SERVICE IS TAKING LONGER THAN EXPECTED " +
                "- TRY AGAIN LATER";
    }

    @GetMapping("/paymentServiceFallbackMethod")
    public String paymentServiceFallbackMethod(){
        return "PAYMENT-SERVICE IS TAKING LONGER THAN EXPECTED " +
                "- TRY AGAIN LATER";
    }

}
