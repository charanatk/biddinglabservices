package com.notify.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notify.app.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	List<Expense> findByUsername(final String username);

}
