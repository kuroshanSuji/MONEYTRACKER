package com.kuroshan.MONEYTRACKER.exception;

import com.kuroshan.MONEYTRACKER.request.Registration;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserAlreadyPresentException extends RuntimeException{
    private final String message="user already present";
    private Registration registration;
}
