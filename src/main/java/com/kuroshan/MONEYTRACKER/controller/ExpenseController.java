package com.kuroshan.MONEYTRACKER.controller;

import com.kuroshan.MONEYTRACKER.request.ExpenseAddReq;
import com.kuroshan.MONEYTRACKER.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exp")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/addExp")
    public void addExpenses(@RequestBody @Valid ExpenseAddReq expenseAddReq)
    {
        // while adding the expenses we need to retrive the user id, without asking user as an input,
        // we need to retrive the info with logged in info.
        expenseService.addExpense(expenseAddReq);
    }
}
