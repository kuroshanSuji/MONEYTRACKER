package com.kuroshan.MONEYTRACKER.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExpenseDeleteReq {
    @NotNull(message = "exp id should not be empty")
    private Integer expId;
}
