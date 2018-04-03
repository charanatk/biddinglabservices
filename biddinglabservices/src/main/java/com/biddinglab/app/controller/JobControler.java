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
import com.biddinglab.app.model.JobBean;
import com.biddinglab.app.repo.JobRep;

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
@RequestMapping("/job/v1")
public class JobControler {

	@Autowired
	JobRep jobRep;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@SuppressWarnings("rawtypes")
	@PostMapping("/savejob")
	ResponseEntity saveJob(@RequestBody JobBean jobBean) {
		logger.info("staring saveJob");
		String errorMsg = "";
		
		Set<ConstraintViolation<JobBean>> constraintViolations = validator.validate(jobBean);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<JobBean> cv : constraintViolations) {
				errorMsg = errorMsg + cv.getMessage();
			}
			errorMsg = errorMsg + "$$";
			JSONObject jsonObj = new JSONObject(errorMsg);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(errorMsg);
		}
		jobRep.saveAndFlush(jobBean);
		logger.info("ending saveJob");
		return new ResponseEntity<JobBean>(jobBean, HttpStatus.OK);
	}

	@GetMapping("/getAllJob")
	ResponseEntity<List<JobBean>> getAllJobs(
			@RequestHeader("userAgent") String userAgent) {

		if (userAgent != null && !userAgent.isEmpty())
			return new ResponseEntity<List<JobBean>>(
					HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<JobBean>>(jobRep.findAll(),
				HttpStatus.OK);
	}
	
	@GetMapping("/error")
	 ResponseEntity<JobBean> errorJob() {
		return new ResponseEntity("Not a valid user",
				HttpStatus.UNAUTHORIZED);
		//return new ResponseEntity<List<JobBean>>(jobRep.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getJob/{JobId}")
	ResponseEntity<JobBean> getJob(
			@PathVariable("JobId") Integer jobId) {
		logger.info("Started getJob");
		if (jobId != null && jobId != 0) {
		logger.info("Ended getJob");
			return new ResponseEntity<JobBean>(
					(JobBean) jobRep.findOne(jobId),
					HttpStatus.OK);
		} else {
			return new ResponseEntity("jobId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}

	@GetMapping("/deleteJob/{JobId}")
	boolean delete(@PathVariable("JobId") Integer JobId) {
		logger.info("Started delete");
		jobRep.delete(JobId);
		logger.info("Ended delete");
		return jobRep.exists(JobId);
	}

	@GetMapping("/getPageJob/{start}/{size}")
	ResponseEntity<List<JobBean>> getPageJob(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size) {
		logger.info("Started getPageJob");

		if (start != null && size != null && size != 0) {
			Iterable<JobBean> bean = jobRep.findAll(new PageRequest(
					start, size));
			ArrayList<JobBean> jobList = new ArrayList<JobBean>();
			if (bean != null) {
				for (JobBean jobBean : bean) {
					jobList.add(jobBean);
				}
		logger.info("Ended getPageJob");
				return new ResponseEntity<List<JobBean>>(jobList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<JobBean>>(jobList,
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity("jobId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
	
	@GetMapping("/getPageJob/{start}/{size}/{field}/{dir}")
	ResponseEntity<List<JobBean>> getSortPageJob(
			@PathVariable("start") Integer start,
			@PathVariable("size") Integer size,
			@PathVariable("dir") String direction,
			@PathVariable("field") String field) throws CustomException {
		logger.info("Started getPageJob");

		if (start != null && size != null && size != 0 && !direction.isEmpty() && direction != null && !field.isEmpty() && field != null) {
			Direction sort = null;
			if(direction.equalsIgnoreCase("ASC"))
				 sort = Sort.Direction.ASC;
			else if(direction.equalsIgnoreCase("DESC")){
				 sort = Sort.Direction.DESC;
			    }
			Iterable<JobBean> bean = jobRep.findAll(new PageRequest(
					start, size,sort,field));
			ArrayList<JobBean> jobList = new ArrayList<JobBean>();
			if (bean != null) {
				for (JobBean jobBean : bean) {
					jobList.add(jobBean);
				}
		logger.info("Ended getPageJob");
				return new ResponseEntity<List<JobBean>>(jobList,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<JobBean>>(jobList,
						HttpStatus.OK);
			}
		} else {
			  ExceptionThrower eT= new ExceptionThrower();
			  //eT.throwGeneralException();
			  eT.throwCustomException();
			  //eT.throwNullPointerException();
			return new ResponseEntity("jobId is mandatry..",
					HttpStatus.PRECONDITION_REQUIRED);
		}
	}
}
