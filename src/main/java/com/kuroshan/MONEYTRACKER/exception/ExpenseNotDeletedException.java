package com.kuroshan.MONEYTRACKER.exception;

import com.kuroshan.MONEYTRACKER.request.ExpenseDeleteReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExpenseNotDeletedException extends RuntimeException{
    private final String message="expense was not deleted";
    private ExpenseDeleteReq expenseDeleteReq;
}
