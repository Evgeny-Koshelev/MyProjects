package com.nc.edu.java.contract.validator;

/*This class is the result of the validation mechanism*/
public class ValidationResult {

	//This field is result of the check:"Ok","Error","Red risk"
	private String resultCheck;
	
	//This field is returns message about error or red risk if if it occurs
	private String message;
	
	//This field is returns the contract number and if resultCheck is error or red risk returns field where it happened.
	private String fieldError;
	
	public String getResultCheck() {
		return resultCheck;
	}
	public void setResultCheck(String resaultCheck) {
		this.resultCheck = resaultCheck;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFieldError() {
		return fieldError;
	}
	public void setFieldError(String fieldError) {
		this.fieldError = fieldError;
	}
}
