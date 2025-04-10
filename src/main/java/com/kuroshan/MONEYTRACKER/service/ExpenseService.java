package com.kuroshan.MONEYTRACKER.service;

import com.kuroshan.MONEYTRACKER.model.Expense;
import com.kuroshan.MONEYTRACKER.repository.ExpenseRepository;
import com.kuroshan.MONEYTRACKER.request.ExpenseAddReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseDeleteReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseGetReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseUpdateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public void addExpense( ExpenseAddReq expenseAddReq) {
        Expense expense=expenseAddReq.toExpense();
        expenseRepository.save(expense);
    }

    public void updateExpenses( ExpenseUpdateReq expenseUpdateReq) {
        Optional<Expense> optionalExpense=expenseRepository.findById(expenseUpdateReq.getExpId());
        if(optionalExpense.isPresent())
        {
            Expense existingExpense=optionalExpense.get();
            if(expenseUpdateReq.getAmt()!=null)
            {
                existingExpense.setAmt(expenseUpdateReq.getAmt());
            }
            if(expenseUpdateReq.getDate()!=null)
            {
                existingExpense.setDate(expenseUpdateReq.getDate());
            }
            if(expenseUpdateReq.getExpenseType()!=null)
            {
                existingExpense.setExpenseType(expenseUpdateReq.getExpenseType());
            }
            expenseRepository.save(existingExpense);
        }
    }

    public void deleteExpense(ExpenseDeleteReq expenseDeleteReq)
    {
        expenseRepository.deleteById(expenseDeleteReq.getExpId());
    }

    public void getExpenses(ExpenseGetReq expenseGetReq)
    {
        if(expenseGetReq.getOnDate()!=null)
        {   if(expenseGetReq.getExpenseType()==null){
            List<Expense> expenseList = expenseRepository.findByOnDate(expenseGetReq.getOnDate());
            }else {
              List<Expense> expenseList=expenseRepository.findByOnDateAndExpenseTypeIn(expenseGetReq.getOnDate(),expenseGetReq.getExpenseType());
            }
        }else if (expenseGetReq.getFromDate()!=null)
        {
            if(expenseGetReq.getToDate()!=null)
            {
                if(expenseGetReq.getExpenseType()!=null)
                {
                    List<Expense> expenseList=expenseRepository.findByFromDateAndToDateAndExpenseTypeIn(
                            expenseGetReq.getFromDate(),expenseGetReq.getToDate(),expenseGetReq.getExpenseType()
                    );
                }else {
                    List<Expense> expenseList = expenseRepository.findByFromDateAndToDate(expenseGetReq.getFromDate(), expenseGetReq.getToDate());
                }
            }else{
                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date=today.format(formatter);
                long longDate=toLong(date);
                Date todayDate = new Date(longDate);
                List<Expense> expenseList = expenseRepository.findByFromDateAndTodayDate(expenseGetReq.getFromDate(),todayDate);
            }
        }else {
            List<Expense> expenseList = expenseRepository.findByExpenseTypeIn(expenseGetReq.getExpenseType());
        }
    }

    public long toLong(String date)
    {
        String hyphenRemovedDate = date.replace(Character.toString('-'), "");
        return Long.parseLong(hyphenRemovedDate);
    }
}
