package com.kuroshan.MONEYTRACKER.exception;

import com.kuroshan.MONEYTRACKER.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController{

    @ExceptionHandler(UserNotFoundException.class)
    public GenericResponse<?> userNotFoundException(UserNotFoundException userNotFoundException)
    {
        return GenericResponse.builder().httpStatusCode(HttpStatusCode.valueOf(400)).httpStatus(HttpStatus.BAD_REQUEST).object(userNotFoundException).build();
    }

    @ExceptionHandler(ExpenseNotAddedException.class)
    public GenericResponse<?> expenseNotAddedException(ExpenseNotAddedException expenseNotAddedException){
        return GenericResponse.builder().httpStatusCode(HttpStatusCode.valueOf(400)).httpStatus(HttpStatus.BAD_REQUEST).object(expenseNotAddedException).build();
    }

    @ExceptionHandler(ExpenseNotDeletedException.class)
    public GenericResponse<?> expenseNotDeletedException(ExpenseNotDeletedException expenseNotDeletedException){
        return GenericResponse.builder().httpStatusCode(HttpStatusCode.valueOf(400)).httpStatus(HttpStatus.BAD_REQUEST).object(expenseNotDeletedException).build();
    }

    @ExceptionHandler(ExpenseNotPresentException.class)
    public GenericResponse<?> expenseNotPresentException(ExpenseNotPresentException expenseNotPresentException){
        return GenericResponse.builder().httpStatusCode(HttpStatusCode.valueOf(400)).httpStatus(HttpStatus.BAD_REQUEST).object(expenseNotPresentException).build();
    }

    @ExceptionHandler(NoExpenseFoundException.class)
    public GenericResponse<?> noExpenseFoundException(NoExpenseFoundException noExpenseFoundException){
        return GenericResponse.builder().httpStatusCode(HttpStatusCode.valueOf(400)).httpStatus(HttpStatus.BAD_REQUEST).object(noExpenseFoundException).build();
    }
    @ExceptionHandler(UserAlreadyPresentException.class)
    public GenericResponse<?> userAlreadyPresentException(UserAlreadyPresentException userAlreadyPresentException){
        return GenericResponse.builder().httpStatusCode(HttpStatusCode.valueOf(400)).httpStatus(HttpStatus.BAD_REQUEST).object(userAlreadyPresentException).build();
    }

    @ExceptionHandler(UserNotAddedException.class)
    public GenericResponse<?> userNotAddedException(UserNotAddedException userNotAddedException){
        return GenericResponse.builder().httpStatusCode(HttpStatusCode.valueOf(400)).httpStatus(HttpStatus.BAD_REQUEST).object(userNotAddedException).build();
    }
}
