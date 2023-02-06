package com.maveric.balanceservice.entity;

import com.maveric.balanceservice.enums.Type;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String _id;
    private Type accountType;
    private String customerId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date updatedAt =new Date();

}
