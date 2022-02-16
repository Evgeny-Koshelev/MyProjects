package com.nc.edu.java.contract.validator;

import com.nc.edu.java.contract.forms.Contract;

/*This class is implements interface Validator.
 * This class checks the person age.
 * */
public class CheckAge implements Validator {

	/*This method checks the age person.
	 *It checks that age person was been more than 14 and less 130.
	 */
	@Override
	public Object checkForValid(Contract contract) {
		ValidationResult resault = new ValidationResult();
		
		if(contract.getClient().getAge() > 13 && contract.getClient().getAge() < 130 )
		{
			resault.setResultCheck("Ok");
			resault.setMessage("");
			resault.setFieldError( "cotract ¹"+ contract.getNumberContract());
		}
		
		else
		{
			resault.setResultCheck("Error");
			resault.setMessage("Invalid customer age. Age of person should be more then 13 years"
        			+ "and less then 130 years");
			resault.setFieldError("Check date Birthday in contract ¹" + contract.getNumberContract());

		}

		return resault;
	}
	

}
