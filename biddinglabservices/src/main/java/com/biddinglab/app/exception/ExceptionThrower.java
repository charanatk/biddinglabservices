package com.biddinglab.app.exception;


public class ExceptionThrower {
	public void throwGeneralException() throws Exception {
		Exception e = new Exception("Error from General Exception");
		throw e;
	}

	public void throwCustomException() throws CustomException {

		CustomException e = new CustomException();
		e.setCode(10);
		e.setMessage("First name not provided");
		throw e;
	}

	public void throwNullPointerException() {
		int i = 1;
		int j;
		j = i / 0;
		System.out.println(j);
	}
}
