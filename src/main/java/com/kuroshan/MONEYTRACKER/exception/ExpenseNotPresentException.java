package com.kuroshan.MONEYTRACKER.exception;

import com.kuroshan.MONEYTRACKER.request.ExpenseUpdateReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExpenseNotPresentException extends RuntimeException{
    private final String message="expense not present";
    private ExpenseUpdateReq expenseUpdateReq;
}
