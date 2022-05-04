package com.nc.edu.java.contract.JAXB;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nc.edu.java.contract.forms.Contract;
import com.nc.edu.java.contract.forms.InternetContract;
import com.nc.edu.java.contract.forms.MobileContract;
import com.nc.edu.java.contract.forms.Person;
import com.nc.edu.java.contract.forms.TvContract;
import com.nc.edu.java.contract.repositoriy.ContractsRepositoriy;

public class JAXBTest {

	ContractsRepositoriy contracts = new ContractsRepositoriy();
	ContractsRepositoriy contracts2 = new ContractsRepositoriy();
	private Contract[] addContracts = new Contract [5];
	Person person = new Person(1, "Alex","1980-11-27", "Man", "4212777888");
	Person personTwo = new Person(2, "Olga","1995-08-17", "Woman", "4212388932");
	
	@Test
	public void testJAXB() {
		fillListContracts();
		JAXBWorker.saveToXml(contracts, "src/main/resources/contracts.xml");
		contracts2 = (ContractsRepositoriy) JAXBWorker.getFromXml("src/main/resources/contracts.xml",ContractsRepositoriy.class);
		Contract[] saveContracts = contracts.getCloneContractsArray();
		Contract[] getContract = contracts2.getCloneContractsArray();
		for (int i = 0; i < saveContracts.length; i++) {
            assertEquals(saveContracts[i].getId(),getContract[i].getId());
        }

		
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
	}

}
