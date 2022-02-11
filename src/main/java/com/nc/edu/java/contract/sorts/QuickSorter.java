package com.nc.edu.java.contract.sorts;

import java.util.Comparator;

import com.nc.edu.java.contract.forms.Contract;


/*This class implements interface ISorted.
 * For sort this class uses quick sort.
 * This method of sort includes some steps:
 * 1.Choose an element from an array;
 * 2.Rearrange the elements in the array in such a way 
 * that elements less than the chosen element are placed before it
 * and those greater than or equal to it are placed after it;
 * 3.Recursively apply the first two steps to the two subarrays
 * to the left and right of the chosen element.
 */ 
public class QuickSorter implements ISorter {

	/* This method takes an array of contracts and comparator 
	 * for rules of sort. If length of an array is equal one, then sorting ends.
	 */

	@Override
	public void sort(Contract[] sortContracts, Comparator<Contract> comparator) {
		
		int number = sortContracts.length;
		if(number > 1)
		{
			Contract[] returnSortContracts = new Contract[number];
			
			returnSortContracts = algorithmQuickSort(sortContracts, 0, number-1, comparator);
			
			for(int i=0; i< number;i++)
			{
				sortContracts[i] = returnSortContracts[i];
			}
			
		}
	}
		
	/* This method takes an array of contracts and comparator 
	 * and returns a sorted array.This method choose element from an array
	 * and compares the other elements with this element. Less - to left,
	 *  greater - to right. Thus two subarrays are formed.
	 *  Then everything is repeated with subarrays.
	 */
	
	 public Contract[] algorithmQuickSort(Contract[] contractsArray, int first, int last, Comparator<Contract> comparator)
     {
         if (first < last)
         {
             int left = first, right = last;
             Contract middleElement = contractsArray[(left + right) / 2];
             do
             {
                 while (comparator.compare(contractsArray[left],middleElement) < 0)
                 {
                     left++;
                 }
                 while (comparator.compare(contractsArray[right],middleElement) > 0)
                 {
                	 
                	 right--;
                 }
                 if (left <= right)
                 {
                     Contract tmp = contractsArray[left];
                     contractsArray[left] = contractsArray[right];
                     contractsArray[right] = tmp;
                     left++;
                     right--;
                 }
             }
             while (left <= right);
             algorithmQuickSort(contractsArray, first, right, comparator);
             algorithmQuickSort(contractsArray, left, last, comparator);
         }
         return contractsArray;
     }

}
