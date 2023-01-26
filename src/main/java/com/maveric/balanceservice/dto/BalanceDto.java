package com.maveric.balanceservice.dto;

import com.maveric.balanceservice.enums.Currency;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor

@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class BalanceDto {
    private String  _id;


    private String accountId;

//    @Min(value = 0,message = "Amount shouldn't be lesser than zero")
    private Number amount;
//    @Valid
//    @NotNull(message = "Currency is mandatory INR/DOLLAR/EURO ")
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date updatedAt =new Date();


}
