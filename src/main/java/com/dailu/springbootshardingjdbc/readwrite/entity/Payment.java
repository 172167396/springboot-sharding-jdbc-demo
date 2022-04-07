package com.dailu.springbootshardingjdbc.readwrite.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {

    private String id;
    private String message;
    private String status;
    private String createTime;
}
