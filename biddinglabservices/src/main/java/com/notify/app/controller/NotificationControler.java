package com.notify.app.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.NotificationBean;
import com.notify.app.repo.NotificationRep;

/**
*
* @author charan kandula
* @version 1.0
* 
* 
*/

@RestController
@RequestMapping("/notification/v1")
public class NotificationControler {

	@Autowired
	NotificationRep notificationRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/saveNotification")
	ResponseEntity<NotificationBean> saveNotification(
			@RequestBody NotificationBean NotificationBean) {
		//if (encoding.equalsIgnoreCase("charan")) {
		logger.info("Staring into saveNotification");
			notificationRep.save(NotificationBean);
		logger.info("Ended saveNotification");
			return new ResponseEntity<NotificationBean>(NotificationBean, HttpStatus.OK);
//		} else {
//			return new ResponseEntity("Not Authorized",
//					HttpStatus.UNAUTHORIZED);
//		}
	}

	@GetMapping("/getAllNotification")
	Iterable<NotificationBean> getAllNotification() {
		logger.info("Staring into getAllNotification");
		logger.info("Ended getAllNotification");
		return notificationRep.findAll();
	}

	@GetMapping("/getNotification/{NotificationId}")
	NotificationBean getNotification(@PathVariable("NotificationId") Integer sprintId) {
		logger.info("Staring into saveNotification");
		logger.info("Ended saveNotification");
		return notificationRep.findOne(sprintId);
	}

	@GetMapping("/deleteNotification/{NotificationId}")
	boolean deleteNotification(@PathVariable("NotificationId") Integer NotificationId) {
		logger.info("Staring into deleteNotification");
		notificationRep.delete(NotificationId);
		logger.info("Ended deleteNotification");
		return notificationRep.exists(NotificationId);
	}
	
	@GetMapping("/getPageNotification/{start}/{size}")
	ResponseEntity<List<NotificationBean>> getPageNotification(@PathVariable("start") Integer start,@PathVariable("size") Integer size) {
		
		logger.info("Stating getPageNotification");
			if (start != null && size != null && size != 0) {
				Iterable<NotificationBean> bean = notificationRep.findAll(new PageRequest(
						start, size));
				ArrayList<NotificationBean> notificationList = new ArrayList<NotificationBean>();
				if (bean != null) {
					for (NotificationBean notificationBean : bean) {
						notificationList.add(notificationBean);
					}
					logger.info("Ended getPageNotification");
					return new ResponseEntity<List<NotificationBean>>(notificationList,
							HttpStatus.OK);
				} else {
					return new ResponseEntity<List<NotificationBean>>(notificationList,
							HttpStatus.OK);
				}
			} else {
				return new ResponseEntity("Mandatry parameter missing..",
						HttpStatus.PRECONDITION_REQUIRED);
			}
    	}
	@GetMapping("/getPageNotification/{start}/{size}/{field}/{dir}")
	ResponseEntity<List<NotificationBean>> getSortPageNotification(@PathVariable("start") Integer start,@PathVariable("size") Integer size) {
		
		logger.info("Stating getSortPageNotification");
			if (start != null && size != null && size != 0) {
				Iterable<NotificationBean> bean = notificationRep.findAll(new PageRequest(
						start, size));
				ArrayList<NotificationBean> notificationList = new ArrayList<NotificationBean>();
				if (bean != null) {
					for (NotificationBean notificationBean : bean) {
						notificationList.add(notificationBean);
					}
					logger.info("Ended getSortPageNotification");
					return new ResponseEntity<List<NotificationBean>>(notificationList,
							HttpStatus.OK);
				} else {
					return new ResponseEntity<List<NotificationBean>>(notificationList,
							HttpStatus.OK);
				}
			} else {
				return new ResponseEntity("Mandatry parameter missing..",
						HttpStatus.PRECONDITION_REQUIRED);
			}
    	}
}
