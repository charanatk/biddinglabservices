package com.notify.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.UserPasswordUpateHistory;
import com.notify.app.model.UserSecureInfo;
import com.notify.app.repo.UpdateUserSecurityRepository;
import com.notify.app.services.UserService;


@RestController
public class UserSecurityUpdateController {

	@Autowired
	private UserService userService;

	@Autowired
	private UpdateUserSecurityRepository updateSecurtiyRepository;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
	public ResponseEntity<String> updatePassword(@RequestBody UserSecureInfo userSecureInfo) {
		try {
			userSecureInfo.setUsername(getLoggedInUserName());
			userService.updateSecurityInfo(userSecureInfo.getPassword(), userSecureInfo.getPasswordConfirm(),
					userSecureInfo.getUsername());
			updateHistory(userSecureInfo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/getSecureInfo", method = RequestMethod.GET)
	public UserSecureInfo getSecureInfo() {
		UserSecureInfo userSecureInfo = new UserSecureInfo();
		userSecureInfo.setUsername(getLoggedInUserName());
		return userSecureInfo;
	}

	@RequestMapping(value = "/updatePasswordHistory", method = RequestMethod.GET)
	public List<UserPasswordUpateHistory> updatePasswordHistory() {
		return updateSecurtiyRepository.findByUsername(getLoggedInUserName());
	}

	public final String getLoggedInUserName() {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		final String username = user.getUsername();
		return username;

	}

	public void updateHistory(UserSecureInfo secureInfo) {
		UserPasswordUpateHistory history = new UserPasswordUpateHistory();
		history.setUsername(secureInfo.getUsername());
		updateSecurtiyRepository.save(history);
	}

}
