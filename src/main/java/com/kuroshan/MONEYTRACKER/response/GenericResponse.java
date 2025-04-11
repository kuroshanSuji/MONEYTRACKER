package com.kuroshan.MONEYTRACKER.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@Builder
public class GenericResponse <T>{
    private HttpStatus httpStatus;
    private HttpStatusCode httpStatusCode;
    private T object;
}
