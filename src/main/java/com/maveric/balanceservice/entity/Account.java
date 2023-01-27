package com.maveric.balanceservice.entity;

import lombok.Data;

import java.lang.reflect.Type;
import java.util.Date;

@Data
public class Account {
    private String _id;

    private Type accountType;

    private String customerId;

    private Date createdAt;

    private Date updatedAt;
}
