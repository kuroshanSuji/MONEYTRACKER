package com.kuroshan.MONEYTRACKER.request;

import com.kuroshan.MONEYTRACKER.model.Expense;
import com.kuroshan.MONEYTRACKER.model.ExpenseType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Date;

@Getter
public class ExpenseAddReq {
    @NotNull(message = "amt should not be null")
    private Double amt;
    @NotNull(message = "date should not be null")
    private Date date;
    @NotNull(message = "expenseType should not be null")
    private ExpenseType expenseType;

    public Expense toExpense()
    {
        return Expense.builder().amt(this.amt).expenseType(this.expenseType).date(this.date).build();
    }
}
