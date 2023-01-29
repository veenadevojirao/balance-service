package com.maveric.balanceservice.entity;

import com.maveric.balanceservice.enums.Type;
import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private String _id;

    private Type Type;

    private String customerId;

    private Date createdAt;

    private Date updatedAt;
}
