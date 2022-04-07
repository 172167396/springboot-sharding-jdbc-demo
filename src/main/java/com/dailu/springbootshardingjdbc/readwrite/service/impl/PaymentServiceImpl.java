package com.dailu.springbootshardingjdbc.readwrite.service.impl;

import com.dailu.springbootshardingjdbc.readwrite.dao.PaymentMapper;
import com.dailu.springbootshardingjdbc.readwrite.entity.Payment;
import com.dailu.springbootshardingjdbc.readwrite.service.PaymentService;
import io.shardingsphere.api.HintManager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentMapper paymentMapper;

    @Override
    public void savePayment(Payment payment) {
        if (!StringUtils.hasLength(payment.getId())) {
            payment.setId(UUID.randomUUID().toString().replace("-", ""));
        }
        paymentMapper.savePayment(payment);
    }

    @Override
    public List<Payment> getPayment(String[] ids) {
        List<String> idList = Arrays.asList(ids);
//        HintManager.getInstance().setMasterRouteOnly();
        return paymentMapper.getPayment(idList);
    }
}
