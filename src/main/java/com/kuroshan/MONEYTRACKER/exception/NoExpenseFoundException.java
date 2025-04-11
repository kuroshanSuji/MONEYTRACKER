package com.kuroshan.MONEYTRACKER.exception;

import com.kuroshan.MONEYTRACKER.request.ExpenseGetReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoExpenseFoundException extends RuntimeException{
    private final String message="no expense found";
    private ExpenseGetReq expenseGetReq;
}
