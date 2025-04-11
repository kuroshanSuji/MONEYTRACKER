package com.kuroshan.MONEYTRACKER.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExpenseDeleteReq {
    @NotNull(message = "exp id should not be empty")
    private Integer expId;
}
