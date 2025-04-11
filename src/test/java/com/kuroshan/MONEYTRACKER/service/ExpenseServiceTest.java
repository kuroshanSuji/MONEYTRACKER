package com.kuroshan.MONEYTRACKER.service;

import com.kuroshan.MONEYTRACKER.model.Expense;
import com.kuroshan.MONEYTRACKER.model.ExpenseType;
import com.kuroshan.MONEYTRACKER.repository.ExpenseRepository;
import com.kuroshan.MONEYTRACKER.request.ExpenseAddReq;
import com.kuroshan.MONEYTRACKER.request.ExpenseUpdateReq;
import com.kuroshan.MONEYTRACKER.response.GenericResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {
    @Mock
    private ExpenseRepository expenseRepository;
    @InjectMocks
    private ExpenseService expenseService;

    @Test
    public void testAddExpense(){
        ExpenseAddReq expenseAddReq= ExpenseAddReq.builder().expenseType(ExpenseType.FOOD)
                .date(new Date(19980205)).amt(1200.00).build();
        Expense expense=Expense.builder().expenseType(ExpenseType.FOOD)
                .date(new Date(19980205)).amt(1200.00).build();
        when(expenseAddReq.toExpense()).thenReturn(expense);
        when(expenseRepository.save(expense)).thenReturn(expense);
        GenericResponse<?> resultFromAddMethod=expenseService.addExpense(expenseAddReq);
        Expense expenseFromMocking= (Expense) resultFromAddMethod.getObject();
        Assertions.assertNotNull(expenseFromMocking);
        Assertions.assertEquals(1200.00,expenseFromMocking.getAmt());
        Assertions.assertEquals(ExpenseType.FOOD,expenseFromMocking.getExpenseType());
    }

    @Test
    public void testUpdateExpenses(){
        ExpenseUpdateReq expenseUpdateReq=ExpenseUpdateReq.builder().expId(1).amt(120.00)
                .date(new Date(19980505)).expenseType(ExpenseType.FOOD).build();
        Expense expense= Expense.builder().amt(120.00)
                .date(new Date(19980505)).eid(1).expenseType(ExpenseType.FOOD).build();
        Optional<Expense> optionalExpense =java.util.Optional.empty();
        when(expenseUpdateReq.getExpId()).thenReturn(1);
        when(expenseRepository.findById(1)).thenReturn(optionalExpense);
        when(false).thenReturn(true);
        when(expenseUpdateReq.getExpenseType()).thenReturn(ExpenseType.FOOD);
        when((expenseUpdateReq.getDate())).thenReturn(new Date(19980505));
        when((expenseUpdateReq.getAmt())).thenReturn(120.00);
        GenericResponse<?> resultFromAddMethod=expenseService.updateExpenses(expenseUpdateReq);
        Expense expenseFromMocking= (Expense) resultFromAddMethod.getObject();
        Assertions.assertNotNull(expenseFromMocking);
        Assertions.assertEquals(1200.00,expenseFromMocking.getAmt());
        Assertions.assertEquals(ExpenseType.FOOD,expenseFromMocking.getExpenseType());
    }
}
