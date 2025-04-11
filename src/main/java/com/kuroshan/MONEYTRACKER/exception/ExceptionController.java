package com.kuroshan.MONEYTRACKER.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController{

    @ExceptionHandler(UserNotFoundException.class)
    public void userNotFoundException(UserNotFoundException userNotFoundException)
    {

    }

    @ExceptionHandler(ExpenseNotAddedException.class)
    public void expenseNotAddedException(ExpenseNotAddedException expenseNotAddedException){

    }

    @ExceptionHandler(ExpenseNotDeletedException.class)
    public void expenseNotDeletedException(ExpenseNotDeletedException expenseNotDeletedException){

    }

    @ExceptionHandler(ExpenseNotPresentException.class)
    public void expenseNotPresentException(ExpenseNotPresentException expenseNotPresentException){

    }

    @ExceptionHandler(NoExpenseFoundException.class)
    public void noExpenseFoundException(NoExpenseFoundException noExpenseFoundException){

    }
    @ExceptionHandler(UserAlreadyPresentException.class)
    public void userAlreadyPresentException(UserAlreadyPresentException userAlreadyPresentException){

    }

    @ExceptionHandler(UserNotAddedException.class)
    public void userNotAddedException(UserNotAddedException userNotAddedException){

    }
}
