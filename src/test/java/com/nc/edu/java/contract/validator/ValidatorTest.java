package com.nc.edu.java.contract.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nc.edu.java.contract.files.ReadFile;
import com.nc.edu.java.contract.repositoriy.ContractsRepositoriy;

public class ValidatorTest {
	
	ContractsRepositoriy contracts = new ContractsRepositoriy();

	@Test
	public void testCheckForValidErrorDateFormat() {
		String path = "src/test/resources/ContractsValidDateErrorFormat.csv";
		Validator validator = new Validator();
		ReadFile readFile = new ReadFile();
		readFile.read(path, contracts, validator);
		Object [] checkValidator = new Object[3];
		Object [] resaultValidator = new Object[3];
		checkValidator[0] = "Error in contract ¹6512";
		checkValidator[1] = "Wrong date format. Exaple:dd.MM.yyyy ";
		checkValidator[2] = "Check field dateInput or field dateOut";
		resaultValidator = validator.getClone();
		for(int i =0;i<3;i++)
		{
			assertEquals(resaultValidator[i],checkValidator[i]);
		}
	}
	
	@Test
	public void testCheckForValidDateOk() {
		String path = "src/test/resources/ContractsValidDateOk.csv";
		Validator validator = new Validator();
		ReadFile readFile = new ReadFile();
		readFile.read(path, contracts, validator);
		Object [] checkValidator = new Object[3];
		Object [] resaultValidator = new Object[3];
		checkValidator[0] = "Ok";
		checkValidator[1] = "";
		checkValidator[2] = "";
		resaultValidator = validator.getClone();
		for(int i =0;i<3;i++)
		{
			assertEquals(resaultValidator[i],checkValidator[i]);
		}
	}
	
	@Test
	public void testCheckForValidDateErrorDate() {
		String path = "src/test/resources/ContractsValidDateError.csv";
		Validator validator = new Validator();
		ReadFile readFile = new ReadFile();
		readFile.read(path, contracts, validator);
		Object [] checkValidator = new Object[3];
		Object [] resaultValidator = new Object[3];
		checkValidator[0] = "Error in contract ¹7031";
		checkValidator[1] = "The end date of the contract cannot be earlier "
    			+ "than the start date of the contract";
		checkValidator[2] = "Check field  dateInput or field dateOut";
		resaultValidator = validator.getClone();
		for(int i =0;i<3;i++)
		{
			assertEquals(resaultValidator[i],checkValidator[i]);
		}
	}
	
	@Test
	public void testCheckForValidAgePersonOk() {
		String path = "src/test/resources/ContractsValidAgePersonOk.csv";
		Validator validator = new Validator();
		ReadFile readFile = new ReadFile();
		readFile.read(path, contracts, validator);
		Object [] checkValidator = new Object[3];
		Object [] resaultValidator = new Object[3];
		checkValidator[0] = "Ok";
		checkValidator[1] = "";
		checkValidator[2] = "";
		resaultValidator = validator.getClone();
		for(int i =0;i<3;i++)
		{
			assertEquals(resaultValidator[i],checkValidator[i]);
		}
	}
	
	@Test
	public void testCheckForValidAgePersonError() {
		String path = "src/test/resources/ContractsValidAgePersonError.csv";
		Validator validator = new Validator();
		ReadFile readFile = new ReadFile();
		readFile.read(path, contracts, validator);
		Object [] checkValidator = new Object[3];
		Object [] resaultValidator = new Object[3];
		checkValidator[0] = "Error in contract ¹6512";
		checkValidator[1] = "Invalid customer age. Age of person should be more then 13 years"
    			+ "and less then 130 years";
		checkValidator[2] = "Check date Birthday";
		resaultValidator = validator.getClone();
		for(int i =0;i<3;i++)
		{
			assertEquals(resaultValidator[i],checkValidator[i]);
		}
	}
	
	@Test
	public void testCheckForValidPassportPersonOk() {
		String path = "src/test/resources/ContractsValidPassportPersonOk.csv";
		Validator validator = new Validator();
		ReadFile readFile = new ReadFile();
		readFile.read(path, contracts, validator);
		Object [] checkValidator = new Object[3];
		Object [] resaultValidator = new Object[3];
		checkValidator[0] = "Ok";
		checkValidator[1] = "";
		checkValidator[2] = "";
		resaultValidator = validator.getClone();
		for(int i =0;i<3;i++)
		{
			assertEquals(resaultValidator[i],checkValidator[i]);
		}
	}
	
	@Test
	public void testCheckForValidPassportPersonErrorLength() {
		String path = "src/test/resources/ContractsValidPassportPersonErrorLength.csv";
		Validator validator = new Validator();
		ReadFile readFile = new ReadFile();
		readFile.read(path, contracts, validator);
		Object [] checkValidator = new Object[3];
		Object [] resaultValidator = new Object[3];
		checkValidator[0] = "Error in contract ¹7031";
		checkValidator[1] = "Invalid customer passport data. Passport must have "
    			+ "10 characters, 4 digits - series and 6 digits - number";
		checkValidator[2] = "Check passport data";
		resaultValidator = validator.getClone();
		for(int i =0;i<3;i++)
		{
			assertEquals(resaultValidator[i],checkValidator[i]);
		}
	}
	
	
	@Test
	public void testCheckForValidPassportPersonErrorSymbols() {
		String path = "src/test/resources/ContractsValidPassportPersonErrorSymbols.csv";
		Validator validator = new Validator();
		ReadFile readFile = new ReadFile();
		readFile.read(path, contracts, validator);
		Object [] checkValidator = new Object[3];
		Object [] resaultValidator = new Object[3];
		checkValidator[0] = "Error in contract ¹7031";
		checkValidator[1] = "Passport must include only numbers";
		checkValidator[2] = "Check passport data";
		resaultValidator = validator.getClone();
		for(int i =0;i<3;i++)
		{
			assertEquals(resaultValidator[i],checkValidator[i]);
		}
	}


}
