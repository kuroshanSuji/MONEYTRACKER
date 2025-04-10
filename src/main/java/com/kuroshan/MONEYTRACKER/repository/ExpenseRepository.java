package com.kuroshan.MONEYTRACKER.repository;

import com.kuroshan.MONEYTRACKER.model.Expense;
import com.kuroshan.MONEYTRACKER.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    public Optional<Expense> findById(Integer id);
    public void deleteById(Integer id);
    public List<Expense> findByOnDate(Date date); //2023-10-15
    public List<Expense> findByOnDateAndExpenseTypeIn(Date onDate, List<ExpenseType> expenseType);
    //no where joinWord is present instead we use And , to search in list use In.
    public List<Expense> findByFromDateAndToDate(Date fromDate,Date toDate);
    public List<Expense> findByFromDateAndTodayDate(Date fromDate,Date todayDate);
    public List<Expense> findByFromDateAndToDateAndExpenseTypeIn(Date fromDate,Date toDate,List<ExpenseType> expenseType);
    public List<Expense> findByExpenseTypeIn(List<ExpenseType> expenseTypes);
}
