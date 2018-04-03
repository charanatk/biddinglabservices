package com.notify.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Override
	public String findLoggedInUsername() {
		String userName = null;
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		userName = (userDetails instanceof UserDetails) ? ((UserDetails) userDetails).getUsername() : null;
		return userName;
	}

	@Override
	public void autologin(String username, String password) {
		boolean isAuthenticated = false;
		UserDetails details = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				details, password, details.getAuthorities());
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		isAuthenticated = (usernamePasswordAuthenticationToken.isAuthenticated()) ? true : false;
		if (isAuthenticated)
			logger.debug(String.format("Auto login %s successfully!", username));

	}

}
