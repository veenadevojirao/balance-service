package com.maveric.balanceservice.entity;

import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String _id;
    private String email;
}
