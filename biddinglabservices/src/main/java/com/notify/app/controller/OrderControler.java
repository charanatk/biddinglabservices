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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.NotificationBean;
import com.notify.app.model.OrderBean;
import com.notify.app.repo.OrderRep;


/**
*
* @author charan kandula
* @version 1.0
* 
* 
*/

@RestController
@RequestMapping("/order/v1")
public class OrderControler {

	@Autowired
	OrderRep orderRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping("/saveOrder")
	ResponseEntity<OrderBean> saveOrder(
			@RequestBody OrderBean OrderBean) {
		//if (encoding.equalsIgnoreCase("charan")) {
		logger.info("Stating into saveOrder");
			orderRep.save(OrderBean);
		logger.info("Ended saveOrder");
			return new ResponseEntity<OrderBean>(OrderBean, HttpStatus.OK);
//		} else {
//			return new ResponseEntity("Unauthorized",
//					HttpStatus.NON_AUTHORITATIVE_INFORMATION);
//		}
	}

	@GetMapping("/getAllOrder")
	Iterable<OrderBean> getAllOrder() {
		logger.info("Stating into getAllOrder");
		logger.info("Ended getAllOrder");
		return orderRep.findAll();
	}

	@GetMapping("/getOrder/{OrderId}")
	OrderBean getOrder(@PathVariable("OrderId") Integer sprintId) {
		logger.info("Stating into getOrder");
		logger.info("Ended getOrder");
		return orderRep.findOne(sprintId);
	}

	@GetMapping("/deleteOrder/{OrderId}")
	boolean delete(@PathVariable("OrderId") Integer OrderId) {
		logger.info("Stating into getOrder");
		orderRep.delete(OrderId);
		logger.info("Ended getOrder");
		return orderRep.exists(OrderId);
	}
	
//	@GetMapping("/getPageOrder/{start}/{size}")
//	Iterable<OrderBean> getPageOrder(@PathVariable("start") Integer start,@PathVariable("size") Integer size) {
//		return orderRep.findAll(new PageRequest(start, size));
//	}
	
	@GetMapping("/getPageNotification/{start}/{size}")
	ResponseEntity<List<OrderBean>> getPageOrder(@PathVariable("start") Integer start,@PathVariable("size") Integer size) {
		
			logger.info("Stating getPageOrder");
			if (start != null && size != null && size != 0) {
				Iterable<OrderBean> bean = orderRep.findAll(new PageRequest(
						start, size));
				ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();
				if (bean != null) {
					for (OrderBean orderBean : bean) {
						orderList.add(orderBean);
					}
					logger.info("Ended getPageOrder");
					return new ResponseEntity<List<OrderBean>>(orderList,
							HttpStatus.OK);
				} else {
					return new ResponseEntity<List<OrderBean>>(orderList,
							HttpStatus.OK);
				}
			} else {
				return new ResponseEntity("Mandatry parameter missing..",
						HttpStatus.PRECONDITION_REQUIRED);
			}
    	}
}
