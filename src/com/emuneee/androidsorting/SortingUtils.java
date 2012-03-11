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
	 * Using the bubble sorting algorithm, sorts an array of integers
	 * O (n^2)
	 * @param integersToSort integers to be sorted
	 * @return array containing a sorted set of integers
	 */
	public static int[] bubbleSortInt(int[] integersToSort) {
		boolean swapped = false;
		int temp;
		numberOfSteps = 0;
		do {
			swapped = false;
			for(int i = 0; i < integersToSort.length; i++) {
				if((i + 1 < integersToSort.length) &&
						integersToSort[i] > integersToSort[i+1]) {
					// swap
					temp = integersToSort[i];
					integersToSort[i] = integersToSort[i+1];
					integersToSort[i+1] = temp;
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
		int temp;
		
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
				temp = integersToSort[i];
				integersToSort[i] = integersToSort[min];
				integersToSort[min] = temp;
			}
		}
		
		
		return integersToSort;
	}
}
