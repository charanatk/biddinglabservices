package com.notify.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.RestaurantBean;
import com.notify.app.repo.RestaurantRep;


/**
*
* @author charan kandula
* @version 1.0
* 
* 
*/

@RestController
@RequestMapping("/restaurant/v1")
public class RestaurantControler {

	@Autowired
	RestaurantRep RestaurantRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/saveRestaurant")
	ResponseEntity<RestaurantBean> saveRestaurant(
			@RequestBody RestaurantBean restaurantBean,
			@RequestHeader("Accept-Encoding") String encoding) {
		logger.info("started saveRestaurant");
		//if (encoding.equalsIgnoreCase("charan")) {
			RestaurantRep.save(restaurantBean);
			logger.info("Ended saveRestaurant");
			return new ResponseEntity<RestaurantBean>(restaurantBean,
					HttpStatus.OK);
		//} else {
		//	return new ResponseEntity("Unauthorized",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		//}
	}

	@GetMapping("/getAllRestaurant")
	Iterable<RestaurantBean> getAllRestaurant() {
		return RestaurantRep.findAll();
	}

	@GetMapping("/getRestaurant/{RestaurantId}")
	ResponseEntity<RestaurantBean> getRestaurant(@PathVariable("RestaurantId") Integer restaurantId) {
		if(null!=restaurantId){
		//return RestaurantRep.findOne(restaurantId)
			RestaurantBean restaurantBean;
			restaurantBean = RestaurantRep.findOne(restaurantId);
			return new ResponseEntity<RestaurantBean>(restaurantBean,HttpStatus.OK);
	}
	else {
				return new ResponseEntity("Mandatry data is missing..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}

	@GetMapping("/deleteRestaurant/{RestaurantId}")
	boolean delete(@PathVariable("RestaurantId") Integer RestaurantId) {
		RestaurantRep.delete(RestaurantId);
		return RestaurantRep.exists(RestaurantId);
	}
	
	@GetMapping("/getPageRestaurants/{start}/{size}")
	ResponseEntity<List<RestaurantBean>> getPageRestaurants(@PathVariable("start") Integer start,@PathVariable("size") Integer size) {
		List<RestaurantBean> restaurantBean;
		restaurantBean=(List<RestaurantBean>) RestaurantRep.findAll(new PageRequest(start, size));
		return new ResponseEntity<List<RestaurantBean>>(restaurantBean,HttpStatus.OK);
	}
	
//	
//	@GetMapping("/getPageRestaurants/{start}/{size}")
//	Iterable<RestaurantBean> getPageRestaurants(@PathVariable("start") Integer start,@PathVariable("size") Integer size) {
//		return RestaurantRep.findAll(new PageRequest(start, size));
//	}
}
