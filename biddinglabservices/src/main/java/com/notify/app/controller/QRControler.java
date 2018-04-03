package com.notify.app.controller;

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
import com.notify.app.model.QRBean;
import com.notify.app.repo.QRRep;



/**
*
* @author charan kandula
* @version 1.0
* 
* 
*/

@RestController
@RequestMapping("/qr/v1")
public class QRControler {

	@Autowired
	QRRep qrRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/saveQR")
	ResponseEntity<QRBean> saveQR(
			@RequestBody QRBean QRBean) {
		//if (encoding.equalsIgnoreCase("charan")) {
			qrRep.save(QRBean);
			return new ResponseEntity<QRBean>(QRBean, HttpStatus.OK);
//		} else {
//			return new ResponseEntity("Unauthorized",
//					HttpStatus.NON_AUTHORITATIVE_INFORMATION);
//		}
	}

	@GetMapping("/getAllQR")
	Iterable<QRBean> getAllQR() {
		return qrRep.findAll();
	}

	@GetMapping("/getQR/{QRId}")
	QRBean getQR(@PathVariable("QRId") Integer sprintId) {
		return qrRep.findOne(sprintId);
	}

	@GetMapping("/deleteQR/{QRId}")
	boolean delete(@PathVariable("QRId") Integer QRId) {
		qrRep.delete(QRId);
		return qrRep.exists(QRId);
	}
	
	@GetMapping("/getPageQR/{start}/{size}")
	Iterable<QRBean> getPageQR(@PathVariable("start") Integer start,@PathVariable("size") Integer size) {
		return qrRep.findAll(new PageRequest(start, size));
	}
}
