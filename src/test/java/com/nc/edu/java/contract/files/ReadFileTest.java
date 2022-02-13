package com.nc.edu.java.contract.files;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nc.edu.java.contract.forms.Contract;
import com.nc.edu.java.contract.forms.InternetContract;
import com.nc.edu.java.contract.forms.MobileContract;
import com.nc.edu.java.contract.forms.Person;
import com.nc.edu.java.contract.forms.TvContract;
import com.nc.edu.java.contract.repositoriy.ContractsRepositoriy;
import com.nc.edu.java.contract.validator.Validator;

public class ReadFileTest {
	
	private Contract[] addContracts = new Contract [5];
	ContractsRepositoriy contracts = new ContractsRepositoriy();

	@Test
	public void testRead() {
		fillListContracts();
		String path = "src/test/resources/Contracts.csv";
		ReadFile readFile = new ReadFile();
		Validator validator = new Validator();
		readFile.read(path, contracts,validator);
		for(int i =0;i<addContracts.length;i++)
		{
			for(int j = 0;j<addContracts[i].getAllFields().length;j++)
			{
				if(j==4)
				{
					assertEquals(((Person)addContracts[i].getAllFields()[j]).getId(),((Person)contracts.getCloneContractsArray()[i].getAllFields()[j]).getId());

				}
				else
					assertEquals(addContracts[i].getAllFields()[j],contracts.getCloneContractsArray()[i].getAllFields()[j]);
			}

		}
		
	}
	
private void fillListContracts() {
		
		Person person = new Person(1, "Alex","27.11.1980", "Man", "4212777888");
		Person personTwo = new Person(2, "Olga","17.08.1995", "Woman", "4212388932");
		InternetContract internetOne = new InternetContract(1, "01.02.2020", "01.03.2020", "6512", person,100);
		MobileContract mobileOne = new MobileContract(2, "01.02.2020", "01.03.2020", "6513", person,300,300,10.00);
		TvContract tvOne = new TvContract(3, "01.02.2020", "01.03.2020", "6514", person,"TvMin");
		InternetContract internetTwo = new InternetContract(4, "01.05.2020", "01.05.2021", "7030", personTwo,500);
		TvContract tvTwo = new TvContract(5, "01.05.2020", "01.05.2021", "7031", personTwo,"TvMin");
		addContracts[0] = internetOne;
		addContracts[1] = mobileOne;
		addContracts[2] = tvOne;
		addContracts[3] = internetTwo;
		addContracts[4] = tvTwo;
	}


}
