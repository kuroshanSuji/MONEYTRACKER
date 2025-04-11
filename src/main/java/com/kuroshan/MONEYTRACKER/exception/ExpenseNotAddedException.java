package com.kuroshan.MONEYTRACKER.exception;

import com.kuroshan.MONEYTRACKER.request.ExpenseAddReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExpenseNotAddedException extends RuntimeException{
    private final String message="expense not added";
    private ExpenseAddReq expenseAddReq;
}
