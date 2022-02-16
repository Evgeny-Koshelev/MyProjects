package com.nc.edu.java.contract.validator;


import com.nc.edu.java.contract.forms.Contract;

/*This interface is validation mechanism. 
 * It checks the contract for validation.
 */ 
public interface Validator {
	
	/*This method checks the contract.*/
	public Object checkForValid(Contract contract);
	
	

	


}
