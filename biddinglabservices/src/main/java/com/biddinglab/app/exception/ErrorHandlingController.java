package com.biddinglab.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> generalException(Exception e)
			throws Exception {

		ExceptionResponse eR = new ExceptionResponse();
		eR.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		eR.setDescription(e.getMessage());
		System.out.println(e);
		return new ResponseEntity<ExceptionResponse>(eR,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> specialException(CustomException e)
			throws Exception {

		ExceptionResponse eR = new ExceptionResponse();
		eR.setCode(HttpStatus.BAD_REQUEST.value());
		eR.setDescription(e.getMessage());

		return new ResponseEntity<ExceptionResponse>(eR, HttpStatus.BAD_REQUEST);
	}

}
