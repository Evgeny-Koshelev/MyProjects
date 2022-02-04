
package com.nc.edu.java.contract.repositoriy;

import com.nc.edu.java.contract.forms.Contract;
import java.lang.RuntimeException;

/*
 * This class is repository for keep contracts.
 * This class contains fields such as number and contracts*/

public class ContractsRepositoriy {
	
    //This field is number of contracts in our repository
	private int number = 0;
	
	//This field is array of contracts of different types
	private Contract[] contracts = new Contract[number];
	
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Contract[] getContracts() {
		return contracts;
	}


	/*
	 * 	This method adds an array of contracts in repository. 
	 * This method takes a new array of contracts and adds this array
	 * in repository at the end of the list
	 */
	public void addContract(Contract[] addListContracts)
	{
		Contract[] newListContracts = new Contract[number];
		for(int i =0; i< contracts.length;i++)
		{
			newListContracts[i] = contracts[i];
		}
		int leghtAddList = addListContracts.length;
		number += leghtAddList;	
		contracts = new Contract [number];
		for(int j=0;j<contracts.length;j++)
		{
			if(j < contracts.length - leghtAddList)
			{
				contracts[j] = newListContracts[j];
			}
			else
			{
				contracts[j] = addListContracts[j - (contracts.length - leghtAddList)];
			}
			
		}

	}
	
	/*
	 * 	This method deletes a a contact from repository.
	 * This method accepts the id of the contract to be deleted, 
	 * checks for a contract with the given id if this id not founded
	 * then nothing happens otherwise, a new array is created with size number-1,
	 * all elements up to the element to be removed are added to this array
	 * and all elements after the element to be removed, number is reset to zero
	 * and new array adds to repository
	 */
	public void deleteContract(int id)
	{
		int check = checkElement(id);
		if(check > -1)
		{
			Contract[] newListContracts = new Contract[number-1];
			for(int i =0;i<contracts.length;i++)
			{
				if(i<check)
					newListContracts[i] = contracts[i];
				if(i>check)
					newListContracts[i-1] = contracts[i];
			}
			number = 0;
			contracts = new Contract[number];
			addContract(newListContracts);
		}

	
	}
	
	/*
	 * 	This method checks for the presence of an element with the given id
	 * This method accepts the id of the contract to be founded and returns
	 * -1 or index this contract in repository.
	 */
	private int checkElement(int id)
	{
		int check = -1;
		for(int i =0; i<contracts.length;i++)
		{
			if(id == contracts[i].getId())
			{
				check = i;
				break;
			}
		}
		return check;
	}
	
	/*
	 * 	This method to get contract data
	 * This method accepts the id of the contract to be founded and returns
	 * null or object of type Contract.
	 */
	public Contract getContract(int id)
	{
		Contract contract = null;
		int check = checkElement(id);
		if(check > -1)
			contract = contracts[check];
		return contract;
	}
	
	/*
	 * 	This method to get contract data
	 * This method accepts the index of the contract in repository
	 * to be founded and returns message "Not founded" or object of type Contract.
	 */
	public Contract getContractByIndex(int index, Class expectedClass)
	{
		Contract contract = contracts[index];
		if (contract.getClass().equals(expectedClass))
			return contract;
		else
		{
			throw new RuntimeException("Not founded");
		}
	}

}
