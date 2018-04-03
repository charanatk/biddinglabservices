package com.notify.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notify.app.model.UserPasswordUpateHistory;


public interface UpdateUserSecurityRepository extends JpaRepository<UserPasswordUpateHistory, String> {

	public List<UserPasswordUpateHistory> findByUsername(final String username);


}
