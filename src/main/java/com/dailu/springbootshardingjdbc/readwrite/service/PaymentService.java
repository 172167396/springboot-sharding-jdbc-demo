package com.dailu.springbootshardingjdbc.readwrite.service;

import com.dailu.springbootshardingjdbc.readwrite.entity.Payment;

import java.util.List;

public interface PaymentService {

    void savePayment(Payment payment);

    List<Payment> getPayment(String[] ids);
}
