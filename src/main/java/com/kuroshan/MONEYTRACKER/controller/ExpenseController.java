package com.kuroshan.MONEYTRACKER.controller;

import com.kuroshan.MONEYTRACKER.request.ExpenseAddReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseDeleteReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseGetReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseUpdateReq;
import com.kuroshan.MONEYTRACKER.response.GenericResponse;
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
    public GenericResponse<?> addExpenses(@RequestBody @Valid ExpenseAddReq expenseAddReq)
    {
        // while adding the expenses we need to retrive the user id, without asking user as an input,
        // we need to retrive the info with logged in info.
        return expenseService.addExpense(expenseAddReq);
    }

    @PostMapping("/updateExp")
    public GenericResponse<?> updateExpenses(@RequestBody @Valid ExpenseUpdateReq expenseUpdateReq)
    {
        return expenseService.updateExpenses(expenseUpdateReq);
    }

    @PostMapping("/deleteExp")
    public GenericResponse<?> deleteExpense(@RequestBody @Valid ExpenseDeleteReq expenseDeleteReq)
    {
        return expenseService.deleteExpense(expenseDeleteReq);
    }

    @GetMapping("/getExp")
    public GenericResponse<?> getExpense(@RequestBody ExpenseGetReq expenseGetReq)
    {
        return expenseService.getExpenses(expenseGetReq);
    }
}
