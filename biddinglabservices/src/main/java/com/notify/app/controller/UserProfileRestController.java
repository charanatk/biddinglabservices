package com.notify.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.User;
import com.notify.app.model.UserProfile;
import com.notify.app.repo.UserRepository;


@RestController
public class UserProfileRestController {

	private Logger PROFILE_CNTRL_LOGGER = Logger.getLogger(UserProfileRestController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/getProfile", method = RequestMethod.POST)
	@Cacheable(value = { "expmgrwebcache" },key = "#p0.username", unless = "#result.username == null")
	public UserProfile getProfile(@RequestBody UserProfile userProfile) {
		PROFILE_CNTRL_LOGGER.info("Enter Into getProfile user : " + userProfile.getUsername() );
		UserProfile profile = userRepository.findByUsername(userProfile.getUsername()).getProfile();
		PROFILE_CNTRL_LOGGER.info("Exit From getProfile");
		return profile;
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.PUT)
	@Caching(evict = {@CacheEvict(value = {"expmgrwebcache"}, key = "#p0.username",condition = "#result.username != null " )}, put = {@CachePut(value = {"expmgrwebcache"}, key = "#p0.username",unless="#result.username == null")})
	public UserProfile updateProfile(@RequestBody UserProfile userProfile) {
		PROFILE_CNTRL_LOGGER.info("Enter Into updateProfile user : " + userProfile.getUsername() );
		User user = userRepository.findByUsername(userProfile.getUsername());
		user.setProfile(userProfile);
		userRepository.save(user);
		PROFILE_CNTRL_LOGGER.info("Exit From updateProfile");
		return userProfile;

	}
	
}
