package com.kuroshan.MONEYTRACKER.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Expense {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer eid;
    private Date date;
    private Double amt;
    private ExpenseType expenseType;
}
