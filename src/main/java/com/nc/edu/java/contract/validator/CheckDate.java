package com.nc.edu.java.contract.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nc.edu.java.contract.forms.Contract;

/*This class is implements interface Validator.
 * This class checks the dates format and that the end date of the contract earlier then the begin date
 * */
public class CheckDate implements Validator {

	/*This method checks the dates. It trying to convert date to format "dd.MM.yyyy"
	 * and checks that the end date of the contract cannot be earlier
	 * than the start date of the contract.
	 */
	@Override
	public Object checkForValid(Contract contract) {
		ValidationResult resault = new ValidationResult();
		int days = 0;
		try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date dateBegin = dateFormat.parse(contract.getDateInput());
            Date dateEnd = dateFormat.parse(contract.getDateOut());	 
            long milliseconds = (dateEnd.getTime() - dateBegin.getTime())/(1000*60*60*24);
            days = (int)milliseconds;
            if(days > 0)
    		{
    			resault.setResultCheck("Ok");
    			resault.setMessage("");
    			resault.setFieldError( "cotract ¹"+ contract.getNumberContract());
    		}
    		else
    		{
    			resault.setResultCheck("Error");
    			resault.setMessage("The end date of the contract cannot be earlier "
            			+ "than the start date of the contract");
    			resault.setFieldError("Check field  dateInput or field dateOut in cotract ¹" + contract.getNumberContract());
    		}
 
        } catch (Exception e) {
        	resault.setResultCheck("Red risk");
			resault.setMessage("Wrong date format. Exaple:dd.MM.yyyy");
			resault.setFieldError("Check field dateInput or field dateOut in contract ¹" + contract.getNumberContract());     	
        }
		
		return resault;
	}
	


}
