/*
 * Copyright 2012 emuneee apps
 * http://emuneee.com/apps
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.emuneee.androidsorting;

/**
 * @author Evan
 *
 */
public class SortingUtils {
	
	public static long numberOfSteps;
	
	/**
	 * Using the quick sorting algorithm, sorts an array of integers
	 * O( nlog(n) )
	 * @param integersToSort integers to be sorted
	 * @return array containing a sorted set of integers
	 */
	public static int[] quickSortInt(int[] integersToSort) {
		quickSort(0, integersToSort.length - 1, integersToSort);
		return integersToSort;
	}
	
	private static void quickSort(int low, int high, int[] integersToSort) {
		int pivot = integersToSort[low + (high - low) / 2];
		int i = low;
		int j = high;
		
		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (integersToSort[i] < pivot) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (integersToSort[j] > pivot) {
				j--;
			}

			// If we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i <= j) {
				exchange(i, j, integersToSort);
				i++;
				j--;
			}
		}
		if (low < j) {
			quickSort(low, j, integersToSort);
		}
		if (i < high) {
			quickSort(i, high, integersToSort);
		}
	}
	
	private static void exchange(int i, int j, int[] integersToSort) {
		int temp = integersToSort[i];
		integersToSort[i] = integersToSort[j];
		integersToSort[j] = temp;
	}
	
	/**
	 * Using the bubble sorting algorithm, sorts an array of integers
	 * O (n^2)
	 * @param integersToSort integers to be sorted
	 * @return array containing a sorted set of integers
	 */
	public static int[] insertionSortInt(int[] integersToSort) {
		int j; // the number of items sorted so far
		int key; // the item to be inserted
		
		numberOfSteps = 0;
		for(int i = 1; i < integersToSort.length; i++) {
			key = integersToSort[i];
			j = i;
			while(j > 0 && integersToSort[j - 1] > key) {
				integersToSort[j] = integersToSort[j - 1];
				j--;
				numberOfSteps++;
			}
			integersToSort[j] = key;
		}
		
		return integersToSort;
	}
	
	/**
	 * Using the bubble sorting algorithm, sorts an array of integers
	 * O (n^2)
	 * @param integersToSort integers to be sorted
	 * @return array containing a sorted set of integers
	 */
	public static int[] bubbleSortInt(int[] integersToSort) {
		boolean swapped = false;
		
		numberOfSteps = 0;
		do {
			swapped = false;
			for(int i = 0; i < integersToSort.length; i++) {
				if((i + 1 < integersToSort.length) &&
						integersToSort[i] > integersToSort[i+1]) {
					// swap
					exchange(i, i+1, integersToSort);
					swapped = true;
					numberOfSteps++;
				}
			}
		} while(swapped);
		return integersToSort;
	}
	
	/**
	 * Using the selection sorting algorithm, sorts an array of integers
	 * O (n^2)
	 * @param integersToSort integers to be sorted
	 * @return array containing a sorted set of integers
	 */
	public static int[] selectionSortInt(int[] integersToSort) {
		int min;
		
		numberOfSteps = 0;
		for(int i = 0; i < integersToSort.length; i++) {
			min = i;
			
			for(int j = i + 1; j < integersToSort.length; j++) {
				numberOfSteps++;
				if(integersToSort[j] < integersToSort[min]) {
					min = j;
				}
			}
			if(min != i) {
				//swap
				exchange(i, min, integersToSort);
			}
		}
		
		
		return integersToSort;
	}
}
