package com.nc.edu.java.contract.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.nc.edu.java.contract.DI.AutoInjectable;
import com.nc.edu.java.contract.forms.Contract;
import com.nc.edu.java.contract.forms.InternetContract;
import com.nc.edu.java.contract.forms.MobileContract;
import com.nc.edu.java.contract.forms.Person;
import com.nc.edu.java.contract.forms.TvContract;
import com.nc.edu.java.contract.repositoriy.ContractsRepositoriy;
import com.nc.edu.java.contract.validator.Validator;
import com.nc.edu.java.contract.validator.CheckAge;
import com.nc.edu.java.contract.validator.CheckDate;
import com.nc.edu.java.contract.validator.CheckPassport;
import com.nc.edu.java.contract.validator.ValidationResult;

/*This class reads files different types and adds 
 * from this files data about of contracts to repository.
 * For correct reading of data from a file, 
 * the strings in it must be separated by a ";" or "/".
 * Each line in this file should have 10 strings 
 * where the ninth string is the type of contract 
 * and the tenth string is unique data for this type of contract.
 */ 
public class ReadFile {
	
	@AutoInjectable
	List<Validator> validators;
	
	/* This method reads files and adds them to repository. 
	 * This method takes a string containing the path to the file to be read and
	 * repository to which you want to add contacts from the file.
     * Method reads all lines from file, separating each line
     * with a ";". or "/" for substrings, adds this string to array of strings, 
     * checks contract by validator and then adds this an array to repository.
	 */
	public void read(String path, ContractsRepositoriy contracts, List<Validator> valid)
	{
		BufferedReader reader = null;
		String line = "";
		validators = valid;

		try {
			reader = new BufferedReader(new FileReader(path));
		
			while((line = reader.readLine()) != null)
			{
				String [] row = line.split(";|\\," );
				int personId = 0;
				try {
					personId =Integer.valueOf(row[0]);
				}
				catch(NumberFormatException e) {
					e.printStackTrace();
				}
				Person person = new Person (personId,row[1],row[2],row[3],row[4]);
				Contract [] contract = new Contract[1];
				int id = contracts.getCloneContractsArray().length+1;
				if(row[8].equals("internet"))
				{
					int speedInternet = 0;
					try {
						speedInternet =Integer.valueOf(row[9]);
					}
					catch(NumberFormatException e) {
						e.printStackTrace();
					}
					contract[0] = new InternetContract(id,row[5],row[6],row[7],person,speedInternet);
				}
				else if(row[8].equals("mobile"))
				{
					int minutes = 0, sms = 0;
					double gb = 0;
					try {
						minutes =Integer.valueOf(row[9]);
						sms =Integer.valueOf(row[10]);
						gb =Double.valueOf(row[11]);
					}
					catch(NumberFormatException e) {
						e.printStackTrace();
					}
					contract[0] = new MobileContract(id,row[5],row[6],row[7],person,
							minutes,sms,gb);
					
				}
				else
				{
					contract[0] = new TvContract(id,row[5],row[6],row[7],person,row[9]);
					
				}
				
				boolean status = validators.stream().map(v->v.checkForValid(contract[0])).
						anyMatch(ob->((ValidationResult) ob).getResultCheck().equals("Error") ||
								((ValidationResult) ob).getResultCheck().equals("Red risk"));
							
				if(!status)
				{
					contracts.addContract(contract);
				}
			    System.out.println("contract is " + status);
						
			}
		}
		catch(Exception e) {
			e.printStackTrace();

		}
		finally
		{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
