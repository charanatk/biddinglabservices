package com.notify.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.notify.app.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

	com.notify.app.model.User findByUsername(String username);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.password=:password, u.passwordConfirm=:passwordConfirm where u.username=:username")
	void updateSecurityInfo(@Param("password") final String password,
			@Param("passwordConfirm") final String passwordConfirm, @Param("username") final String username);
}
