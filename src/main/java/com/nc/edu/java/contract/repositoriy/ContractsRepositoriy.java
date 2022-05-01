
package com.nc.edu.java.contract.repositoriy;

import com.nc.edu.java.contract.DI.AutoInjectable;
import com.nc.edu.java.contract.forms.Contract;
import com.nc.edu.java.contract.forms.InternetContract;
import com.nc.edu.java.contract.forms.MobileContract;
import com.nc.edu.java.contract.forms.Person;
import com.nc.edu.java.contract.forms.TvContract;
import com.nc.edu.java.contract.sorts.ISorter;
import com.nc.edu.java.contract.sorts.MergeSorter;
import com.nc.edu.java.contract.sorts.QuickSorter;

import java.lang.RuntimeException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.function.Predicate;

/*
 * This class is repository for keep contracts.
 * This class contains fields such as number and contracts*/

public class ContractsRepositoriy {

    //This field is number of contracts in our repository
	private int number = 0;
	
	//This field is array of contracts of different types
	private Contract[] contracts = new Contract[number];
	
//	private ISorter sorter = new MergeSorter();
	@AutoInjectable
	private ISorter sorter;
	
	/*
	 * 	This method adds an array of contracts in repository. 
	 * This method takes a new array of contracts and adds this array
	 * in repository at the end of the list
	 */
	public void addContract(Contract[] addListContracts)
	{
		Contract[] newListContracts = new Contract[number];
		newListContracts = getCloneContractsArray();
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
	 * 	This method creates a clone of an array of contracts this repository. 
	 * This method copy and adds ever element of an array of contracts in new an array 
	 * and returns this new array.
	 */
	public Contract[] getCloneContractsArray()
	{
		Contract[] cloneContracts = new Contract[number];
		for(int i =0; i< contracts.length;i++)
		{
			cloneContracts[i] = contracts[i];
		}
		return cloneContracts;
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
	
	/*
	 * 	This method sorts the array elements of this repository 
	 * by the passed comparator.
	 */
	public void sort(Comparator<Contract> comparator)
	{
		sorter.sort(contracts, comparator);
	}
	
	/*
	 * 	This method search the array elements of this repository that 
	 * satisfy the conditions of the passed predicate and returns the object
	 * of this class that includes these elements.
	 * 
	 */
	public ContractsRepositoriy search(Predicate<Contract> predicate)
	{
		int indexElements = 0;
		Contract[] listContracts = new Contract[1];
		ContractsRepositoriy contractsSearch = new ContractsRepositoriy();

		for(int i=0;i<contracts.length;i++)
		{
			if(predicate.test(contracts[i]))
			{
				listContracts[indexElements] = contracts[i];
				contractsSearch.addContract(listContracts);

			}
		}

		return contractsSearch;
	}
	
	public void saveToDB(Connection cn) throws SQLException {
        PreparedStatement preparedStatement;
        for (int i = 0; i < contracts.length; i++) {
            if(contracts[i].getClass() == TvContract.class){
                TvContract tvContract = (TvContract) contracts[i];
                preparedStatement = cn.prepareStatement("insert into tv_contracts "
                		+ "(id, date_input, date_out, number_contract, client_id, pakege_of_canals) "
                		+ "values (?, ?, ?, ?, ?, ?)");
                preparedStatement.setInt(1, tvContract.getId());
                try {
                	//SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                	java.sql.Date dateOfIn = java.sql.Date.valueOf(tvContract.getDateInput());
                	java.sql.Date dateOfOut = java.sql.Date.valueOf(tvContract.getDateOut());
                	preparedStatement.setDate(2, dateOfIn);
                    preparedStatement.setDate(3, dateOfOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                preparedStatement.setString(4, tvContract.getNumberContract());
                preparedStatement.setInt(5, tvContract.getClient().getId());
                preparedStatement.setString(6, tvContract.getPakegeofcanals());
                preparedStatement.executeUpdate();
            }
            else if(contracts[i].getClass() == MobileContract.class){
                MobileContract mobileContract = (MobileContract) contracts[i];
                preparedStatement = cn.prepareStatement("insert into mobile_contracts "
                		+ "(id, date_input, date_out, number_contract, client_id, number_of_minutes, number_of_sms, gbs) "
                		+ "values (?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setInt(1, mobileContract.getId());
                try {
                	java.sql.Date dateOfIn = java.sql.Date.valueOf(mobileContract.getDateInput());
                	java.sql.Date dateOfOut = java.sql.Date.valueOf(mobileContract.getDateOut());
                	preparedStatement.setDate(2, dateOfIn);
                    preparedStatement.setDate(3, dateOfOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                preparedStatement.setString(4, mobileContract.getNumberContract());
                preparedStatement.setInt(5, mobileContract.getClient().getId());
                preparedStatement.setInt(6, mobileContract.getMinutes());
                preparedStatement.setInt(7, mobileContract.getSms());
                preparedStatement.setFloat(8,mobileContract.getGb().floatValue());
                preparedStatement.executeUpdate();
            }
            else if (contracts[i].getClass() == InternetContract.class){
                InternetContract internetContract = (InternetContract) contracts[i];
                preparedStatement = cn.prepareStatement("insert into internet_contracts "
                		+ "(id, date_input, date_out, number_contract, client_id, speed) "
                		+ "values (?, ?, ?, ?, ?, ?)");
                preparedStatement.setInt(1, internetContract.getId());
                try {
                	java.sql.Date dateOfIn = java.sql.Date.valueOf(internetContract.getDateInput());
                	java.sql.Date dateOfOut = java.sql.Date.valueOf(internetContract.getDateOut());
                	preparedStatement.setDate(2, dateOfIn);
                    preparedStatement.setDate(3, dateOfOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                preparedStatement.setString(4, internetContract.getNumberContract());
                preparedStatement.setInt(5, internetContract.getClient().getId());
                preparedStatement.setInt(6, internetContract.getSpeed());
                preparedStatement.executeUpdate();
            }
        }
    }

    public void getFromDB(Connection cn) throws SQLException { 	
        PreparedStatement preparedStatementTvContract = cn.prepareStatement("select * from tv_contracts");
        ResultSet resultSet = preparedStatementTvContract.executeQuery();
        while (resultSet.next()){
        	Contract[] getContracts = new Contract[1];
            Person person = Person.getFromDBById(cn, resultSet.getInt("client_id"));
            TvContract tvContract = new TvContract(resultSet.getInt("id"), 
            		new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("date_input")), 
            		new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("date_out")), 
            		resultSet.getString("number_contract"), person, resultSet.getString("pakege_of_canals"));
            getContracts[0] = tvContract;
            addContract(getContracts);
        }

        PreparedStatement preparedStatementMobileContract = cn.prepareStatement("select * from mobile_contracts");
        resultSet = preparedStatementMobileContract.executeQuery();
        while (resultSet.next()){
        	Contract[] getContracts = new Contract[1];
            Person person = Person.getFromDBById(cn, resultSet.getInt("client_id"));
            MobileContract mobileContract = new MobileContract(resultSet.getInt("id"), 
            		new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("date_input")), 
            		new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("date_out")), 
            		resultSet.getString("number_contract"), 
            		person,
            		resultSet.getInt("number_of_minutes"), 
            		resultSet.getInt("number_of_sms"), 
            		resultSet.getFloat("gbs"));
            getContracts[0] = mobileContract;
            addContract(getContracts);
        }

        PreparedStatement preparedStatementInternetContract = cn.prepareStatement("select * from internet_contracts");
        resultSet = preparedStatementInternetContract.executeQuery();
        while (resultSet.next()){
        	Contract[] getContracts = new Contract[1];
            Person person = Person.getFromDBById(cn, resultSet.getInt("client_id"));
            InternetContract internetContract = new InternetContract(resultSet.getInt("id"), 
            		new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("date_input")), 
            		new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("date_out")), 
            		resultSet.getString("number_contract"),  
            		person, 
            		resultSet.getInt("speed"));
            getContracts[0] = internetContract;
            addContract(getContracts);

        }
    }

}
