package com.kuroshan.MONEYTRACKER.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserNotFoundException extends RuntimeException{
    private final String message="user not found";
    private Integer userId;
}
