package com.kuroshan.MONEYTRACKER.request;

import com.kuroshan.MONEYTRACKER.model.ExpenseType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Date;

@Getter
public class ExpenseUpdateReq {
    @NotNull(message = "exp id should not be null")
    private Integer expId;
    private Double amt;
    private Date date;
    private ExpenseType expenseType;
}
