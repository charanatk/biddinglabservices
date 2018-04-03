package com.notify.app.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notify.app.model.User;
import com.notify.app.repo.RoleRepository;
import com.notify.app.repo.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
    BCryptPasswordEncoder passwordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepo.findAll()));
		userRepo.save(user);
	}

	@Override
	public User findByUsername(final String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public void updateSecurityInfo(String password, String passwordConfirm, String username) {
		userRepo.updateSecurityInfo(passwordEncoder.encode(password), passwordConfirm, username);
	}

}
