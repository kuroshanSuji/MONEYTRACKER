package com.kuroshan.MONEYTRACKER.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;
}
