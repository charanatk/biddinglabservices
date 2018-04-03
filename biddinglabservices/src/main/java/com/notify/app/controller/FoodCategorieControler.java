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
import com.notify.app.model.FoodCategorieBean;
import com.notify.app.repo.FoodCategorieRep;

/**
 *
 * @author charan kandula
 * @version 1.0
 * 
 * 
 */
@RestController
@RequestMapping("/catagorie/v1")
public class FoodCategorieControler {

	@Autowired
	FoodCategorieRep foodCtgRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("rawtypes")
	@PostMapping("/savecatagorie")
	ResponseEntity saveCatagorie(
			@RequestBody FoodCategorieBean catagorieBean) {
				
	      logger.info("staring saveCatagorie");
			if (catagorieBean.getCategorieName() != null
					&& catagorieBean.getCategorieName() != ""
					&& catagorieBean.getRestaurantId() != null
					&& catagorieBean.getRestaurantId() != 0) {
				foodCtgRep.save(catagorieBean);
		 logger.info("ending saveCatagorie");
				return new ResponseEntity<FoodCategorieBean>(catagorieBean,
						HttpStatus.OK);
		} else {
			String errorMsg = "{\"code\": 6328, \"message\": \"An error occurred!\"}";
			
		//	ResponseEntity<FoodCategorieBean> responseEntity = new ResponseEntity<FoodCategorieBean>(catagorieBean,HttpStatus.PRECONDITION_REQUIRED);
//				return new ResponseEntity(catagorieBean,
//					HttpStatus.PRECONDITION_REQUIRED);
			
				return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMsg);
		}
	}

	@GetMapping("/getAllCategorie")
	ResponseEntity<List<FoodCategorieBean>> getAllCategorie(
			@RequestHeader("userAgent") String userAgent) {

		if (userAgent != null && !userAgent.isEmpty())
			return new ResponseEntity<List<FoodCategorieBean>>(
					HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<FoodCategorieBean>>(foodCtgRep.findAll(),
				HttpStatus.OK);
		// return null;
	}
	
	@GetMapping("/error")
	 ResponseEntity<FoodCategorieBean> errorCategorie() {
		return new ResponseEntity("CategorieId is mandatry..",
				HttpStatus.UNAUTHORIZED);
		//return new ResponseEntity<List<FoodCategorieBean>>(foodCtgRep.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getCategorie/{CategorieId}")
	ResponseEntity<FoodCategorieBean> getCategorie(
			@PathVariable("categorieId") Integer categorieId) {
		logger.info("Started getCategorie");
		if (categorieId != null && categorieId != 0) {
		logger.info("Ended getCategorie");
			return new ResponseEntity<FoodCategorieBean>(
					(FoodCategorieBean) foodCtgRep.findOne(categorieId),
					HttpStatus.OK);
		} else {
			return new ResponseEntity("categorieId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}

	@GetMapping("/deleteCategorie/{CategorieId}")
	boolean delete(@PathVariable("CategorieId") Integer CategorieId) {
		logger.info("Started delete");
		foodCtgRep.delete(CategorieId);
		logger.info("Ended delete");
		return foodCtgRep.exists(CategorieId);
	}

	@GetMapping("/getPageCategorie/{start}/{size}")
	ResponseEntity<List<FoodCategorieBean>> getPageCategorie(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size) {
		logger.info("Started getPageCategorie");

		if (start != null && size != null && size != 0) {
			Iterable<FoodCategorieBean> bean = foodCtgRep.findAll(new PageRequest(
					start, size));
			ArrayList<FoodCategorieBean> catagorieList = new ArrayList<FoodCategorieBean>();
			if (bean != null) {
				for (FoodCategorieBean catagorieBean : bean) {
					catagorieList.add(catagorieBean);
				}
		logger.info("Ended getPageCategorie");
				return new ResponseEntity<List<FoodCategorieBean>>(catagorieList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<FoodCategorieBean>>(catagorieList,
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity("start,size are mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
	
	@GetMapping("/getPageCategorie/{start}/{size}/{field}/{dir}")
	ResponseEntity<List<FoodCategorieBean>> getSortPageCategorie(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size,
			@PathVariable("dir") String direction,
			@PathVariable("field") String field) throws CustomException {
		logger.info("Started getPageCategorie");

		if (start != null && size != null && size != 0 && !direction.isEmpty() && direction != null && !field.isEmpty() && field != null) {
			Direction sort = null;
			if(direction.equalsIgnoreCase("ASC"))
				 sort = Sort.Direction.ASC;
			else if(direction.equalsIgnoreCase("DESC")){
				 sort = Sort.Direction.DESC;
			    }
			Iterable<FoodCategorieBean> bean = foodCtgRep.findAll(new PageRequest(
					start, size,sort,field));
			ArrayList<FoodCategorieBean> catagorieList = new ArrayList<FoodCategorieBean>();
			if (bean != null) {
				for (FoodCategorieBean catagorieBean : bean) {
					catagorieList.add(catagorieBean);
				}
		logger.info("Ended getPageCategorie");
				return new ResponseEntity<List<FoodCategorieBean>>(catagorieList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<FoodCategorieBean>>(catagorieList,
						HttpStatus.OK);
			}
		} else {
			  ExceptionThrower eT= new ExceptionThrower();
			  //eT.throwGeneralException();
			  eT.throwCustomException();
			  //eT.throwNullPointerException();
			return new ResponseEntity("start,size,field,dir are mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
}
