package com.biddinglab.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Validation;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biddinglab.app.exception.CustomException;
import com.biddinglab.app.exception.ExceptionThrower;
import com.biddinglab.app.model.BidderBean;
import com.biddinglab.app.repo.BidderRep;

import javax.validation.ConstraintViolation; 
import javax.validation.Validator; 

/**
 *
 * @author charan kandula
 * @version 1.0
 * 
 * 
 */
@RestController
@RequestMapping("/bidder/v1")
public class BidderControler {

	@Autowired
	BidderRep bidderRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@SuppressWarnings("rawtypes")
	@PostMapping("/savebidder")
	ResponseEntity saveBidder(@RequestBody BidderBean bidderBean) {
		logger.info("staring saveBidder");
		String errorMsg = "";
		
		Set<ConstraintViolation<BidderBean>> constraintViolations = validator.validate(bidderBean);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<BidderBean> cv : constraintViolations) {
				errorMsg = errorMsg + cv.getMessage();
			}
			errorMsg = errorMsg + "$$";
			JSONObject jsonObj = new JSONObject(errorMsg);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(errorMsg);
		}
		bidderRep.saveAndFlush(bidderBean);
		logger.info("ending saveBidder");
		return new ResponseEntity<BidderBean>(bidderBean, HttpStatus.OK);
	}

	@GetMapping("/getAllBidder")
	ResponseEntity<List<BidderBean>> getAllBidders(
			@RequestHeader("userAgent") String userAgent) {

		if (userAgent != null && !userAgent.isEmpty())
			return new ResponseEntity<List<BidderBean>>(
					HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<BidderBean>>(bidderRep.findAll(),
				HttpStatus.OK);
	}
	
	@GetMapping("/error")
	 ResponseEntity<BidderBean> errorBidder() {
		return new ResponseEntity("Not a valid user",
				HttpStatus.UNAUTHORIZED);
		//return new ResponseEntity<List<BidderBean>>(bidderRep.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getBidder/{BidderId}")
	ResponseEntity<BidderBean> getBidder(
			@PathVariable("BidderId") Integer bidderId) {
		logger.info("Started getBidder");
		if (bidderId != null && bidderId != 0) {
		logger.info("Ended getBidder");
			return new ResponseEntity<BidderBean>(
					(BidderBean) bidderRep.findOne(bidderId),
					HttpStatus.OK);
		} else {
			return new ResponseEntity("bidderId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}

	@GetMapping("/deleteBidder/{BidderId}")
	boolean delete(@PathVariable("BidderId") Integer BidderId) {
		logger.info("Started delete");
		bidderRep.delete(BidderId);
		logger.info("Ended delete");
		return bidderRep.exists(BidderId);
	}

	@GetMapping("/getPageBidder/{start}/{size}")
	ResponseEntity<List<BidderBean>> getPageBidder(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size) {
		logger.info("Started getPageBidder");

		if (start != null && size != null && size != 0) {
			Iterable<BidderBean> bean = bidderRep.findAll(new PageRequest(
					start, size));
			ArrayList<BidderBean> bidderList = new ArrayList<BidderBean>();
			if (bean != null) {
				for (BidderBean bidderBean : bean) {
					bidderList.add(bidderBean);
				}
		logger.info("Ended getPageBidder");
				return new ResponseEntity<List<BidderBean>>(bidderList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<BidderBean>>(bidderList,
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity("bidderId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
	
	@GetMapping("/getPageBidder/{start}/{size}/{field}/{dir}")
	ResponseEntity<List<BidderBean>> getSortPageBidder(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size,
			@PathVariable("dir") String direction,
			@PathVariable("field") String field) throws CustomException {
		logger.info("Started getPageBidder");

		if (start != null && size != null && size != 0 && !direction.isEmpty() && direction != null && !field.isEmpty() && field != null) {
			Direction sort = null;
			if(direction.equalsIgnoreCase("ASC"))
				 sort = Sort.Direction.ASC;
			else if(direction.equalsIgnoreCase("DESC")){
				 sort = Sort.Direction.DESC;
			    }
			Iterable<BidderBean> bean = bidderRep.findAll(new PageRequest(
					start, size,sort,field));
			ArrayList<BidderBean> bidderList = new ArrayList<BidderBean>();
			if (bean != null) {
				for (BidderBean bidderBean : bean) {
					bidderList.add(bidderBean);
				}
		logger.info("Ended getPageBidder");
				return new ResponseEntity<List<BidderBean>>(bidderList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<BidderBean>>(bidderList,
						HttpStatus.OK);
			}
		} else {
			  ExceptionThrower eT= new ExceptionThrower();
			  //eT.throwGeneralException();
			  eT.throwCustomException();
			  //eT.throwNullPointerException();
			return new ResponseEntity("bidderId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
}
