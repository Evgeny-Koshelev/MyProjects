package com.nc.edu.java.contract.sorts;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import com.nc.edu.java.contract.forms.Contract;
import com.nc.edu.java.contract.forms.InternetContract;
import com.nc.edu.java.contract.forms.MobileContract;
import com.nc.edu.java.contract.forms.Person;
import com.nc.edu.java.contract.forms.TvContract;
import com.nc.edu.java.contract.repositoriy.ContractsRepositoriy;

public class MergeSorterTest {

	ContractsRepositoriy contracts = new ContractsRepositoriy();
	private Contract[] addContracts = new Contract [5];
	private Contract[] checkMergeSort = new Contract [5];

	
	@Test
	public void sortTest() {
		fillListContracts();
		Comparator<Contract> comparatorOne = new Comparator<Contract>() {
			public int compare(Contract o1, Contract o2) {
			    return o1.getId().compareTo(o2.getId());
			}
		};
		contracts.sort(comparatorOne);
		
		for(int i =0;i<checkMergeSort.length;i++)
		{
			for(int j = 0;j<checkMergeSort[i].getAllFields().length;j++)
			{
				assertEquals(checkMergeSort[i].getAllFields()[j],contracts.getCloneContractsArray()[i].getAllFields()[j]);
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
		checkMergeSort[0] = internetOne;
		checkMergeSort[1] = mobileOne;
		checkMergeSort[2] = tvOne;
		checkMergeSort[3] = internetTwo;
		checkMergeSort[4] = tvTwo;
		addContracts[0] = mobileOne;
		addContracts[1] = internetOne;
		addContracts[2] = tvOne;
		addContracts[3] = tvTwo;
		addContracts[4] = internetTwo;
		contracts.addContract(addContracts);
 
	}

}
