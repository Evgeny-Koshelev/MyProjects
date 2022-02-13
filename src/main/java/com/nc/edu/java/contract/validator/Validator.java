package com.nc.edu.java.contract.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nc.edu.java.contract.forms.Contract;

/*This class is contract validation mechanism. 
 * It checks the contract according to three criteria: dates, 
 * age person and passport date.
 */ 
public class Validator {
	
	private Object[] resaultCheck = new Object[3];
	
	/*This method checks the contract according to three criteria:
	 * 1. Checks the dates if everything is ok then goes to the second step 
	 * if not(wrong date format or the end date of the contract earlier 
	 * then the begin date) then sends an error message and reader file is close.
	 * 2.Checks the age of person if everything is ok then goes to the third step 
	 * if not(age < 14 or age> 130) then sends an error message and reader file is close.
	 * 3. Checks the passport dates if the passport does not have 10 characters
	 * or passport data  or the passport data contains letters
	 * then sends an error message and reader file is close.
	 */ 
	public Object[] checkForValid(Contract contract)
	{

		checkDates(contract);
		if(resaultCheck[0].equals("Ok"))
		{
			checkAgePerson(contract);
			if(resaultCheck[0].equals("Ok"))
			{
				checkPassportPerson(contract);
			}
		}
		return resaultCheck;
	}
	
	/*This method checks the dates. It trying to convert date to format "dd.MM.yyyy"
	 * and checks that the end date of the contract cannot be earlier
	 * than the start date of the contract.
	 */
	private void checkDates(Contract contract)
	{

		int days = 0;
		try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date dateBegin = dateFormat.parse(contract.getDateInput());
            Date dateEnd = dateFormat.parse(contract.getDateOut());	 
            long milliseconds = (dateEnd.getTime() - dateBegin.getTime())/(1000*60*60*24);
            days = (int)milliseconds;
            if(days > 0)
    		{
    			resaultCheck[0] = "Ok";
    			resaultCheck[1] = "";
    			resaultCheck[2] = "";
    		}
    		else
    		{
            	resaultCheck[0] = "Error in contract ¹" + contract.getNumberContract();
            	resaultCheck[1] = "The end date of the contract cannot be earlier "
            			+ "than the start date of the contract";
            	resaultCheck[2] = "Check field  dateInput or field dateOut";   
    		}
 
        } catch (Exception e) {
        	resaultCheck[0] = "Error in contract ¹" + contract.getNumberContract();
        	resaultCheck[1] = "Wrong date format. Exaple:dd.MM.yyyy ";
        	resaultCheck[2] = "Check field dateInput or field dateOut";     	
        }
		
	}
	/*This method checks the age person.
	 *It checks that age person was been more than 14 and less 130.
	 */
	private void checkAgePerson(Contract contract)
	{
		if(contract.getClient().getAge() > 13 && contract.getClient().getAge() < 130 )
		{
			resaultCheck[0] = "Ok";
			resaultCheck[1] = "";
			resaultCheck[2] = "";
		}
		
		else
		{
			resaultCheck[0] = "Error in contract ¹" + contract.getNumberContract();
        	resaultCheck[1] = "Invalid customer age. Age of person should be more then 13 years"
        			+ "and less then 130 years";
        	resaultCheck[2] = "Check date Birthday";   
		}
	}
	
	/*This method checks the passport data. It checks that the passport 
	 * consists of 10 characters and that these characters are all numbers.
	 */
	private void checkPassportPerson(Contract contract)
	{
		if(contract.getClient().getPassport().length() == 10)
		{
			try {
				Long.valueOf(contract.getClient().getPassport());
				resaultCheck[0] = "Ok";
				resaultCheck[1] = "";
				resaultCheck[2] = "";
			}
			catch(NumberFormatException e) {
				resaultCheck[0] = "Error in contract ¹" + contract.getNumberContract();
				resaultCheck[1] = "Passport must include only numbers";
				resaultCheck[2] = "Check passport data";
			}
			
		}
		
		else
		{
			resaultCheck[0] = "Error in contract ¹" + contract.getNumberContract();
        	resaultCheck[1] = "Invalid customer passport data. Passport must have "
        			+ "10 characters, 4 digits - series and 6 digits - number";
        	resaultCheck[2] = "Check passport data";   
		}
	}
	
	/*
	 * 	This method creates a clone of an array of objects this class. 
	 * This method copy and adds ever element of an array in new an array 
	 * and returns this new array.
	 */
	public Object[] getClone()
	{
		Object [] cloneValidator = new Object[3];
		cloneValidator[0] = resaultCheck[0];
		cloneValidator[1] = resaultCheck[1];
		cloneValidator[2] = resaultCheck[2];
		return cloneValidator;
	}

}
