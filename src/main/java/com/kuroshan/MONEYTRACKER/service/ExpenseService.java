package com.kuroshan.MONEYTRACKER.service;

import com.kuroshan.MONEYTRACKER.exception.ExpenseNotAddedException;
import com.kuroshan.MONEYTRACKER.exception.ExpenseNotDeletedException;
import com.kuroshan.MONEYTRACKER.exception.ExpenseNotPresentException;
import com.kuroshan.MONEYTRACKER.exception.NoExpenseFoundException;
import com.kuroshan.MONEYTRACKER.model.Expense;
import com.kuroshan.MONEYTRACKER.repository.ExpenseRepository;
import com.kuroshan.MONEYTRACKER.request.ExpenseAddReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseDeleteReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseGetReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseUpdateReq;
import com.kuroshan.MONEYTRACKER.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    public GenericResponse<?> addExpense(ExpenseAddReq expenseAddReq) {
        Expense expense;
        try{ expense=expenseAddReq.toExpense();
        expenseRepository.save(expense);
        } catch (Exception e) {
            throw ExpenseNotAddedException.builder().expenseAddReq(expenseAddReq).build();
        }
        return GenericResponse.builder().httpStatusCode(HttpStatus.valueOf(200)).httpStatus(HttpStatus.CREATED).object(expense).build();
    }

    public GenericResponse<?> updateExpenses( ExpenseUpdateReq expenseUpdateReq) {
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
        }else{
            throw ExpenseNotPresentException.builder().expenseUpdateReq(expenseUpdateReq).build();
        }
        return GenericResponse.builder().httpStatusCode(HttpStatus.valueOf(200)).httpStatus(HttpStatus.OK).object(optionalExpense.get()).build();
    }

    public GenericResponse<?> deleteExpense(ExpenseDeleteReq expenseDeleteReq)
    {
        try{
            expenseRepository.deleteById(expenseDeleteReq.getExpId());
        } catch (Exception e) {
            throw ExpenseNotDeletedException.builder().expenseDeleteReq(expenseDeleteReq).build();
        }
        return GenericResponse.builder().httpStatus(HttpStatus.OK).httpStatusCode(HttpStatusCode.valueOf(200)).build();
    }

    public GenericResponse<?> getExpenses(ExpenseGetReq expenseGetReq)
    {
        if(expenseGetReq.getOnDate()!=null)
        {   if(expenseGetReq.getExpenseType()==null){
              List<Expense> expenseList = expenseRepository.findByOnDate(expenseGetReq.getOnDate());
              return GenericResponse.builder().httpStatus(HttpStatus.OK).httpStatusCode(HttpStatusCode.valueOf(200)).object(expenseList).build();
            }else {
              List<Expense> expenseList=expenseRepository.findByOnDateAndExpenseTypeIn(expenseGetReq.getOnDate(),expenseGetReq.getExpenseType());
              return GenericResponse.builder().httpStatus(HttpStatus.OK).httpStatusCode(HttpStatusCode.valueOf(200)).object(expenseList).build();
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
                    return GenericResponse.builder().httpStatus(HttpStatus.OK).httpStatusCode(HttpStatusCode.valueOf(200)).object(expenseList).build();
                }else {
                    List<Expense> expenseList = expenseRepository.findByFromDateAndToDate(expenseGetReq.getFromDate(), expenseGetReq.getToDate());
                    return GenericResponse.builder().httpStatus(HttpStatus.OK).httpStatusCode(HttpStatusCode.valueOf(200)).object(expenseList).build();
                }
            }else{
                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date=today.format(formatter);
                long longDate=toLong(date);
                Date todayDate = new Date(longDate);
                List<Expense> expenseList = expenseRepository.findByFromDateAndTodayDate(expenseGetReq.getFromDate(),todayDate);
                return GenericResponse.builder().httpStatus(HttpStatus.OK).httpStatusCode(HttpStatusCode.valueOf(200)).object(expenseList).build();
            }
        }else if(expenseGetReq.getExpenseType()!=null){
            List<Expense> expenseList = expenseRepository.findByExpenseTypeIn(expenseGetReq.getExpenseType());
            return GenericResponse.builder().httpStatus(HttpStatus.OK).httpStatusCode(HttpStatusCode.valueOf(200)).object(expenseList).build();
        }
        // if not found, in any of the above
            // then need to throw exception
        throw NoExpenseFoundException.builder().expenseGetReq(expenseGetReq).build();
    }

    public long toLong(String date)
    {
        String hyphenRemovedDate = date.replace(Character.toString('-'), "");
        return Long.parseLong(hyphenRemovedDate);
    }
}
