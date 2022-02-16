package com.nc.edu.java.contract.validator;

import com.nc.edu.java.contract.forms.Contract;

/*This class is implements interface Validator.
 * This class checks the person passport.
 * */
public class CheckPassport implements Validator {

	/*This method checks the passport data. It checks that the passport 
	 * consists of 10 characters and that these characters are all numbers.
	 */
	@Override
	public Object checkForValid(Contract contract) {
		ValidationResult resault = new ValidationResult();

		if(contract.getClient().getPassport().length() == 10)
		{
			try {
				Long.valueOf(contract.getClient().getPassport());
				resault.setResultCheck("Ok");
				resault.setMessage("");
				resault.setFieldError( "cotract ¹"+ contract.getNumberContract());
			}
			catch(NumberFormatException e) {
				resault.setResultCheck("Red risk");
				resault.setMessage("Passport must include only numbers");
				resault.setFieldError("Check passport data in contract" + contract.getNumberContract());
			}
			
		}
		
		else
		{
			resault.setResultCheck("Error");
			resault.setMessage("Invalid customer passport data. Passport must have "
        			+ "10 characters, 4 digits - series and 6 digits - number");
			resault.setFieldError("Check passport data in contract ¹" + contract.getNumberContract());  
		}
		return resault;
	}
	

}
