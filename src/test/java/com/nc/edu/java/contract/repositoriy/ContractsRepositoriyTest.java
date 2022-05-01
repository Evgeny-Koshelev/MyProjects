package com.nc.edu.java.contract.repositoriy;

import static org.junit.Assert.*;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Comparator;
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
	Person person = new Person(1, "Alex","1980-11-27", "Man", "4212777888");
	Person personTwo = new Person(2, "Olga","1995-08-17", "Woman", "4212388932");
	private static Connection cn;
	
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
			assertEquals(addContracts[4].getAllFields()[i],int1.getAllFields()[i]);
		}

	}
	
	
	@Test
	public void testGetContractByIndex() {
		fillListContracts();
		InternetContract int1 = (InternetContract)contracts.getContractByIndex(3,InternetContract.class);
		for(int i=0;i<int1.getAllFields().length;i++)
		{
			assertEquals(addContracts[3].getAllFields()[i],int1.getAllFields()[i]);
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
	
	@Test
	public void testSaveJDBC() throws SQLException {
		fillListContracts();
		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
	
		 try{
	            cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Contracts", "postgres", "123");
	            ScriptRunner sr = new ScriptRunner(cn);
	            sr.setLogWriter(null);
	            sr.runScript(new InputStreamReader(cn.getClass().getClassLoader().getResourceAsStream("contracts_table.sql")));
	        }
	        catch (SQLException e){
	            cn = null;
	        }
		
        
		try {
			person.saveToDB(cn);
			personTwo.saveToDB(cn);
			contracts.saveToDB(cn);
			contracts2.getFromDB(cn);
			Contract[] saveContracts = contracts.getCloneContractsArray();
			Contract[] getContract = contracts2.getCloneContractsArray();
			for (int i = 0; i < saveContracts.length; i++) {
                assertEquals(saveContracts[i].getId(),getContract[i].getId());
            }
		}
		catch (SQLException e){
        cn = null;
        }
		 cn.close();
	}
	
	private void fillListContracts() {
		
	
		InternetContract internetOne = new InternetContract(1, "2020-02-01", "2020-03-01", "6512", person,100);
		MobileContract mobileOne = new MobileContract(2, "2020-02-01", "2020-03-01", "6513", person,300,300,10.00);
		TvContract tvOne = new TvContract(3, "2020-02-01", "2020-03-01", "6514", person,"TvMin");
		InternetContract internetTwo = new InternetContract(4, "2020-05-01", "2021-05-01", "7030", personTwo,500);
		TvContract tvTwo = new TvContract(6, "2020-05-01", "2021-05-01", "7031", personTwo,"TvMin");
		addContracts[0] = tvOne;
		addContracts[1] = tvTwo;
		addContracts[2] = mobileOne;
		addContracts[3] = internetOne;
		addContracts[4] = internetTwo;
		contracts.addContract(addContracts);
		checkDeleteContract[0] = tvTwo;
		checkDeleteContract[1] = mobileOne;
		checkDeleteContract[2] = internetOne;
		checkDeleteContract[3] = internetTwo;
		//checkSearch[0] = internetOne;
		checkSearch[0] = internetTwo;
		//checkSearch[2] = internetTwo; 
	}

}
