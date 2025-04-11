package com.kuroshan.MONEYTRACKER.request;

import com.kuroshan.MONEYTRACKER.model.ExpenseType;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
@Builder
public class ExpenseGetReq {
    private Date fromDate;
    private Date toDate;
    private Date onDate;
    private List<ExpenseType> expenseType;

}
