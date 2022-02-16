package com.nc.edu.java.contract.validator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.nc.edu.java.contract.forms.Contract;
import com.nc.edu.java.contract.forms.InternetContract;
import com.nc.edu.java.contract.forms.MobileContract;
import com.nc.edu.java.contract.forms.Person;
import com.nc.edu.java.contract.forms.TvContract;


public class ValidatorTest {
	
	Boolean [] checkValidator = new Boolean[11];
	Contract [] resultValidator = new Contract[11];

	@Test
	public void testCheckForValidErrorDateFormat() {
		fillListsForCheck();
		List <Validator> validators = Arrays.asList(new CheckDate(), new CheckAge(), new CheckPassport());
		
		for(int i = 0;i<resultValidator.length;i++)
		{
			Contract contract = resultValidator[i];
			boolean status = validators.stream().map(v->v.checkForValid(contract)).
					anyMatch(ob->((ValidationResult) ob).getResultCheck().equals("Error") ||
							((ValidationResult) ob).getResultCheck().equals("Red risk"));
			assertEquals(status,checkValidator[i]);
		}
			
			

	}
	private void fillListsForCheck() {
		Person alex = new Person(1, "Alex","27.11.1980", "Man", "4212777888");
		Person olga = new Person(2, "Olga","17.08.1995","Woman","4212388932");
		Person anton = new Person(3, "Anton","27.11.1880","Man","42127718835");
		Person anna = new Person(4, "Anna","12.07.1999","Woman","4213385532");
		Person danil = new Person(5, "Danil","27.11.1970","Man","4212123888");
		Person oleg = new Person(6, "Oleg","17.08.1993","Man","4212");
		Person valentina = new Person(7, "Valentina","17.08.1965","Woman","42AB3889cd");

		
		checkValidator [0] = false;
		checkValidator [1] = false;
		checkValidator [2] = false;
		checkValidator [3] = false;
		checkValidator [4] = false;
		checkValidator [5] = true;
		checkValidator [6] = true;
		checkValidator [7] = true;
		checkValidator [8] = true;
		checkValidator [9] = true;
		checkValidator [10] = true;
				
		
		resultValidator [0] = new InternetContract(1, "01.02.2020", "01.03.2020", "6512", alex, 100); 
		resultValidator [1] = new MobileContract(2, "01.02.2020", "01.03.2020", "6513", alex, 300, 300, 10.00); 
		resultValidator [2] = new TvContract(3, "01.02.2020","01.03.2020", "6514", alex, "TvMin"); 
		resultValidator [3] = new InternetContract(4, "01.05.2020", "01.05.2021", "7030", olga, 500); 
		resultValidator [4] = new TvContract(5, "01.05.2020", "01.05.2021", "7031", olga, "TvMin"); 
		resultValidator [5] = new InternetContract(6, "01.02.2020", "01.03.2020", "6700", anton, 100); 
		resultValidator [6] = new TvContract(7, "01.05.2021", "01.05.2020", "7000", anna, "TvMin"); 
		resultValidator [7] = new InternetContract(8, "01-02-2020", "01-03-2020", "6812", danil, 100); 
		resultValidator [8] = new TvContract(9, "01/05/2020", "01/05/2021", "6931", danil, "TvMin"); 
		resultValidator [9] = new TvContract(10, "01.05.2020", "01.05.2021", "7031", oleg, "TvMin"); 
		resultValidator [10] = new TvContract(11, "01.05.2020", "01.05.2021", "7531", valentina, "TvMin"); 

	}
	
	
	

	
}
