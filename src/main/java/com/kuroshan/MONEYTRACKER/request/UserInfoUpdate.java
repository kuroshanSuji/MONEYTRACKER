package com.kuroshan.MONEYTRACKER.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter // this is mandatory
public class UserInfoUpdate {
    @NotEmpty(message = "userId should not be empty")
    private Integer userId;
    private String name;
    private String email;
    private String password;
}
