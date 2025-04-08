package com.kuroshan.MONEYTRACKER.repository;

import com.kuroshan.MONEYTRACKER.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
}
