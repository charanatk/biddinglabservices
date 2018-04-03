package com.notify.app.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.notify.app.model.FavMenuBean;
import com.notify.app.repo.FavMenuRep;

/**
 *
 * @author charan kandula
 * @version 1.0
 * 
 * 
 */
@RestController
@RequestMapping("/favMenu/v1")
public class FavMenuControler {

	@Autowired
	FavMenuRep favMenuRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("rawtypes")
	@PostMapping("/savefavMenu")
	ResponseEntity savefavMenu(
			@RequestBody FavMenuBean favMenuBean) {
				
	      logger.info("staring savefavMenu");
			if (favMenuBean.getCustomerID() != null
					&& favMenuBean.getCustomerID() != 0
					&& favMenuBean.getItemId() != null
					&& favMenuBean.getItemId() != 0
					&& favMenuBean.getRestaurantId() != null
					&& favMenuBean.getRestaurantId() != 0) {
				favMenuRep.save(favMenuBean);
		 logger.info("ending savefavMenu");
				return new ResponseEntity<FavMenuBean>(favMenuBean,
						HttpStatus.OK);
		} else {
			String errorMsg = "{\"code\": 6328, \"message\": \"An error occurred!\"}";
			
		//	ResponseEntity<CustomerBean> responseEntity = new ResponseEntity<CustomerBean>(customerBean,HttpStatus.PRECONDITION_REQUIRED);
//				return new ResponseEntity(customerBean,
//					HttpStatus.PRECONDITION_REQUIRED);
			
				return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMsg);
		}
	}

	@GetMapping("/getAllFavMenu")
	ResponseEntity<List<FavMenuBean>> getAllFavMenu(
			@RequestHeader("userAgent") String userAgent) {

		if (userAgent != null && !userAgent.isEmpty())
			return new ResponseEntity<List<FavMenuBean>>(
					HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<FavMenuBean>>(favMenuRep.findAll(),
				HttpStatus.OK);
		// return null;
	}
	
	@GetMapping("/error")
	 ResponseEntity<FavMenuBean> errorCustomer() {
		return new ResponseEntity("customerId is mandatry..",
				HttpStatus.UNAUTHORIZED);
		//return new ResponseEntity<List<CustomerBean>>(favMenuRep.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getFavMemu/{menuId}")
	ResponseEntity<FavMenuBean> getFavMenu(
			@PathVariable("menuId") Integer favId) {
		logger.info("Started getFavMemu");
		if (favId != null && favId != 0) {
		logger.info("Ended getFavMenu");
			return new ResponseEntity<FavMenuBean>(
					(FavMenuBean) favMenuRep.findOne(favId),
					HttpStatus.OK);
		} else {
			return new ResponseEntity("menuId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}

	@GetMapping("/deleteFavMenu/{menuId}")
	boolean deleteFavMenu(@PathVariable("menuId") Integer menuId) {
		logger.info("Started deleteFavMenu");
		favMenuRep.delete(menuId);
		logger.info("Ended deleteFavMenu");
		return favMenuRep.exists(menuId);
	}

	@GetMapping("/getPageFavMenu/{start}/{size}")
	ResponseEntity<List<FavMenuBean>> getPageFavMenu(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size) {
		logger.info("Started getPageFavMenu");

		if (start != null && size != null && size != 0) {
			Iterable<FavMenuBean> bean = favMenuRep.findAll(new PageRequest(
					start, size));
			ArrayList<FavMenuBean> favMenuList = new ArrayList<FavMenuBean>();
			if (bean != null) {
				for (FavMenuBean favBean : bean) {
					favMenuList.add(favBean);
				}
		logger.info("Ended getPageFavMenu");
				return new ResponseEntity<List<FavMenuBean>>(favMenuList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<FavMenuBean>>(favMenuList,
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity("start,size are mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
	
	@GetMapping("/getPageFavMenu/{start}/{size}/{field}/{dir}")
	ResponseEntity<List<FavMenuBean>> getSortPageFavMenu(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size,
			@PathVariable("dir") String direction,
			@PathVariable("field") String field) throws CustomException {
		logger.info("Started getSortPageFavMenu");

		if (start != null && size != null && size != 0 && !direction.isEmpty() && direction != null && !field.isEmpty() && field != null) {
			Direction sort = null;
			if(direction.equalsIgnoreCase("ASC"))
				 sort = Sort.Direction.ASC;
			else if(direction.equalsIgnoreCase("DESC")){
				 sort = Sort.Direction.DESC;
			    }
			Iterable<FavMenuBean> bean = favMenuRep.findAll(new PageRequest(
					start, size,sort,field));
			ArrayList<FavMenuBean> favMenuList = new ArrayList<FavMenuBean>();
			if (bean != null) {
				for (FavMenuBean favBean : bean) {
					favMenuList.add(favBean);
				}
		logger.info("Ended getSortPageFavMenu");
				return new ResponseEntity<List<FavMenuBean>>(favMenuList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<FavMenuBean>>(favMenuList,
						HttpStatus.OK);
			}
		} else {
			  ExceptionThrower eT= new ExceptionThrower();
			  //eT.throwGeneralException();
			  eT.throwCustomException();
			  //eT.throwNullPointerException();
			return new ResponseEntity("start,size,dir,field are mandatry fields..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
}
