package com.kuroshan.MONEYTRACKER.service;

import com.kuroshan.MONEYTRACKER.model.Expense;
import com.kuroshan.MONEYTRACKER.repository.ExpenseRepository;
import com.kuroshan.MONEYTRACKER.request.ExpenseAddReq;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public void addExpense( ExpenseAddReq expenseAddReq) {
        Expense expense=expenseAddReq.toExpense();
        expenseRepository.save(expense);
    }
}
