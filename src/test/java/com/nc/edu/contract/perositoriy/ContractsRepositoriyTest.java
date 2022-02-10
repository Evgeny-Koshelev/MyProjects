package com.nc.edu.contract.perositoriy;

import static org.junit.Assert.*;

import java.util.function.Predicate;

import org.junit.Test;

import com.nc.edu.java.contract.forms.Contract;
import com.nc.edu.java.contract.forms.InternetContract;
import com.nc.edu.java.contract.forms.MobileContract;
import com.nc.edu.java.contract.forms.Person;
import com.nc.edu.java.contract.forms.TvContract;
import com.nc.edu.java.contract.repositoriy.ContractsRepositoriy;

public class ContractsRepositoriyTest {

	ContractsRepositoriy contracts = new ContractsRepositoriy();
	ContractsRepositoriy contracts2 = new ContractsRepositoriy();
	private Contract[] addContracts = new Contract [5];
	private Contract[] checkDeleteContract = new Contract [4];
	private Contract[] checkSearch = new Contract [2];
	
	@Test
	public void testAddContract() {
		fillListContracts();
		//assertArrayEquals(addContracts,contracts.getContracts());
		for(int i =0;i<addContracts.length;i++)
		{
			for(int j = 0;j<addContracts[i].getAllFields().length;j++)
			{
				assertEquals(addContracts[i].getAllFields()[j],contracts.getCloneContractsArray()[i].getAllFields()[j]);
			}

		}

	}


	@Test
	public void testGetContract() {
		fillListContracts();
		InternetContract int1 = (InternetContract)contracts.getContract(4);
		for(int i=0;i<int1.getAllFields().length;i++)
		{
			assertEquals(addContracts[3].getAllFields()[i],int1.getAllFields()[i]);
		}

	}
	
	
	@Test
	public void testGetContractByIndex() {
		fillListContracts();
		InternetContract int1 = (InternetContract)contracts.getContractByIndex(0,InternetContract.class);
		for(int i=0;i<int1.getAllFields().length;i++)
		{
			assertEquals(addContracts[0].getAllFields()[i],int1.getAllFields()[i]);
		}
	}
	
	@Test
	public void testDeleteContract() {
		fillListContracts();
		contracts.deleteContract(3);
		for(int i =0;i<checkDeleteContract.length;i++)
		{
			for(int j = 0;j<checkDeleteContract[i].getAllFields().length;j++)
			{
				assertEquals(checkDeleteContract[i].getAllFields()[j],contracts.getCloneContractsArray()[i].getAllFields()[j]);
			}

		}
	}
	
	@Test
	public void testSearch() {
		fillListContracts();
		//Predicate<Contract> predicateOne = contract -> contract.getId() < 4;
		//Predicate<Contract> predicateOne = contract -> contract.getClass().equals(InternetContract.class);
		Predicate<Contract> predicateOne = contract -> contract instanceof InternetContract && ((InternetContract)contract).getSpeed() > 100;
		contracts2 = contracts.search(predicateOne);
		for(int i =0;i<contracts2.getCloneContractsArray().length;i++)
		{
			for(int j = 0;j<contracts2.getCloneContractsArray()[i].getAllFields().length;j++)
			{
				assertEquals(contracts2.getCloneContractsArray()[i].getAllFields()[j],checkSearch[i].getAllFields()[j]);
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
		TvContract tvTwo = new TvContract(6, "01.05.2020", "01.05.2021", "7031", personTwo,"TvMin");
		addContracts[0] = internetOne;
		addContracts[1] = mobileOne;
		addContracts[2] = tvOne;
		addContracts[3] = internetTwo;
		addContracts[4] = tvTwo;
		contracts.addContract(addContracts);
		checkDeleteContract[0] = internetOne;
		checkDeleteContract[1] = mobileOne;
		checkDeleteContract[2] = internetTwo;
		checkDeleteContract[3] = tvTwo;
		//checkSearch[0] = internetOne;
		checkSearch[0] = internetTwo;
		//checkSearch[2] = internetTwo; 
	}

}
