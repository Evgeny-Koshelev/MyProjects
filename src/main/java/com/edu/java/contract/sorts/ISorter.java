package com.edu.java.contract.sorts;

import java.util.Comparator;

import com.nc.edu.java.contract.forms.Contract;

public interface ISorter {
	
	public void sort(Contract[] sortContracts, Comparator<Contract> comparator);

}
