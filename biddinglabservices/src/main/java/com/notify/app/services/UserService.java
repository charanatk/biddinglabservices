package com.notify.app.services;

import com.notify.app.model.User;

public interface UserService {

	void save(User user);

	User findByUsername(String username);

	void updateSecurityInfo(final String password, final String passwordConfirm, final String username);

}
