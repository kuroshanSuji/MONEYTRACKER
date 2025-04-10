package com.kuroshan.MONEYTRACKER.controller;

import com.kuroshan.MONEYTRACKER.request.ExpenseAddReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseDeleteReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseGetReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseUpdateReq;
import com.kuroshan.MONEYTRACKER.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/updateExp")
    public void updateExpenses(@RequestBody @Valid ExpenseUpdateReq expenseUpdateReq)
    {
        expenseService.updateExpenses(expenseUpdateReq);
    }

    @PostMapping("/deleteExp")
    public void deleteExpense(@RequestBody @Valid ExpenseDeleteReq expenseDeleteReq)
    {
        expenseService.deleteExpense(expenseDeleteReq);
    }

    @GetMapping("/getExp")
    public void getExpense(@RequestBody ExpenseGetReq expenseGetReq)
    {
        expenseService.getExpenses(expenseGetReq);
    }
}
