package com.maveric.balanceservice.entity;

import com.maveric.balanceservice.enums.Constants;
import com.maveric.balanceservice.enums.Currency;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "balance")
public class Balance {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String _id;


    private String accountId;

    private Number amount;

//    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date updatedAt =new Date();
}
