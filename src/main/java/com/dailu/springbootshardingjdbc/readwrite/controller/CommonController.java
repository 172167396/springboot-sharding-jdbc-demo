package com.dailu.springbootshardingjdbc.readwrite.controller;

import com.dailu.springbootshardingjdbc.readwrite.entity.Payment;
import com.dailu.springbootshardingjdbc.readwrite.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CommonController {

    @Resource
    PaymentService paymentService;


    @PostMapping("/savePayment")
    public String savePayment(@RequestBody Payment payment) {
        paymentService.savePayment(payment);
        return "success";
    }

    @GetMapping("/getPayment")
    public List<Payment> getPayment(String[] ids) {
        return paymentService.getPayment(ids);
    }

    @GetMapping("/count")
    public int count(){
        return paymentService.count();
    }
}
