package com.nc.edu.java.contract.sorts;

import java.util.Comparator;
import com.nc.edu.java.contract.forms.Contract;

/*This class implements interface ISorted.
 * For sort this class uses merge sort.
 * This method of sort includes some steps:
 * 1.The array to be sorted is split into two parts of the same size;
 * 2.Each of the resulting parts is sorted separately;
 * 3.Two half-sized ordered arrays are merged into one.
 */ 
public class MergeSorter implements ISorter  {

	/* This method takes an array of contracts and comparator 
	 * for rules of sort. If length of an array is equal one, then sorting ends.
	 */

	@Override
	public void sort(Contract[] sortContracts, Comparator<Contract> comparator) {
		int number = sortContracts.length;
		if(number > 1)
		{
			Contract[] returnSortContracts = new Contract[number];
			returnSortContracts = spliteArray(sortContracts,comparator);
			for(int i=0; i< number;i++)
			{
				sortContracts[i] = returnSortContracts[i];
			}
			
		}
		
		

	}
	
	/* This method takes an array of contracts and comparator 
	 * and returns a sorted array. 
	 */

	private Contract[] spliteArray(Contract[] contractsArray, Comparator<Contract> comparator) {
		if(contractsArray.length == 1)
		{
			return contractsArray;
		}
		
		int middle = contractsArray.length/2;
		
		return merge(spliteArray(takeArray(contractsArray,middle),comparator),spliteArray(skipArray(contractsArray,middle),comparator),comparator);
	}
	
	/* This method returns returns the given number
	 * from the beginning of the sequence.
	 * This method takes an array of contracts and 
	 * the required number of contiguous elements 
	 * from the beginning of the array.
	 */
	private Contract[] takeArray(Contract[] fullArray, int numberElements)
	{
		Contract[] returnArray = new Contract[numberElements];
		for(int i = 0; i < numberElements;i++)
		{
			returnArray[i] = fullArray[i];
		}
		
		return returnArray;
	}
	
	/* This method skips a given number of elements in a sequence 
	 * and returns the remaining elements.
	 * This method takes an array of contracts and 
	 * the number of elements to skip.*/
	private Contract[] skipArray(Contract[] fullArray, int numberElements)
	{
		Contract[] returnArray;
		if(fullArray.length % 2 ==0)
			returnArray = new Contract[numberElements];
		else
			returnArray = new Contract[numberElements+1];

		for(int i = numberElements; i < fullArray.length;i++)
		{
			returnArray[i-numberElements] = fullArray[i];
		}
		
		return returnArray;
	}
	
	/* This method compares the elements of two arrays 
	 * and returns an ordered array including of the elements 
	 * of the given arrays.
	 * This method takes two arrays of contracts and comparator
	 * to rule sort*/
	private Contract[] merge (Contract[]arrayLeft,Contract[] arrayRight, Comparator<Contract> comparator)
	{
		 int left = 0, right = 0;
		 
		 Contract[] mergedArray = new Contract[arrayLeft.length + arrayRight.length];
		 
		 for (int i = 0; i < arrayLeft.length + arrayRight.length; i++)
         {
             if (left < arrayLeft.length && right < arrayRight.length)
             {
            	 if(comparator.compare(arrayLeft[left],arrayRight[right])>0)
            	 {
            		 mergedArray[i] = arrayRight[right++];
            	 }
            	 
            	 else
            	 {
            		 mergedArray[i] = arrayLeft[left++];
            	 }

             }
             else
             {
                 if (right < arrayRight.length)
                 {
                	 mergedArray[i] = arrayRight[right++];
                 }
                 else
                 {
            		 mergedArray[i] = arrayLeft[left++];
                 }
             }
         }
		 
		 return mergedArray;
	}


}
