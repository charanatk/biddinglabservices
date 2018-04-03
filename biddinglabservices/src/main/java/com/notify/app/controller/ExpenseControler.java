package com.notify.app.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.Expense;


@RestController
public class ExpenseControler {

	private static Logger EXPENSE_CONTROLLER_LOGGER = Logger.getLogger(ExpenseControler.class);

	@Autowired
	private com.notify.app.repo.ExpenseRepository expenseRepository;

	
	@RequestMapping("/helloCache/{name}")
	@Cacheable(value = { "expmgrwebcache" },key = "#p0")
	public String helloCache(@PathVariable final String name) {
		EXPENSE_CONTROLLER_LOGGER.info("Enter into helloCache : " +name);
		EXPENSE_CONTROLLER_LOGGER.info("Exit From helloCache : " + name);
		return "Hello Mr . " + name;
	}

	@RequestMapping("/loadExpTemplate")
	public Expense loadExpTemplate() {
		EXPENSE_CONTROLLER_LOGGER.info("Enter into loadExpTemplate");
		Expense e = new Expense(getLoggedInUserName());
		EXPENSE_CONTROLLER_LOGGER.info("Exit from loadExpTemplate by user :" + e.getUsername());
		return e;
	}

	@RequestMapping(value = "/addNewExpense", method = RequestMethod.PUT)
	Expense addNewExpense(@RequestBody Expense expense) {
		return expenseRepository.save(expense);
	}

	@RequestMapping("/expenses")
	List<Expense> expenses() {
		return expenseRepository.findByUsername(getLoggedInUserName());
	}

	@Cacheable(value = { "expmgrwebcache" })
	public final String getLoggedInUserName() {
		EXPENSE_CONTROLLER_LOGGER.info("Enter into getLoggedInUserName");
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		final String username = user.getUsername();
		EXPENSE_CONTROLLER_LOGGER.info("Exit from getLoggedInUserName by user :" + username);
		return username;
	}

}
