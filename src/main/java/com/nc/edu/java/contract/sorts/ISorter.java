package com.nc.edu.java.contract.sorts;

import java.util.Comparator;

import com.nc.edu.java.contract.forms.Contract;

/*
 * It is interface to sort elements of an array in repository.
 */

public interface ISorter {
	
	/* This method takes an array of contracts and comparator for rules of sort*/
	public void sort(Contract[] sortContracts, Comparator<Contract> comparator);

}
