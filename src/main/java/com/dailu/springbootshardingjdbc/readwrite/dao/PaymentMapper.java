package com.dailu.springbootshardingjdbc.readwrite.dao;

import com.dailu.springbootshardingjdbc.readwrite.entity.Payment;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

@Mapper
public interface PaymentMapper {

    @Insert("insert into tb_pay (id,message,status,create_time) values(#{id},#{message},#{status},#{createTime})")
    void savePayment(Payment payment);

    @Select("<script>select id,message,status,create_time from tb_pay where id in <foreach collection=\"ids\" item=\"id\" separator=\",\" open=\"(\" close=\")\">#{id}</foreach></script>")
    List<Payment> getPayment(@Param("ids") Collection<String> ids);
}
