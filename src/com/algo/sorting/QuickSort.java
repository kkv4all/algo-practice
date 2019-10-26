package com.algo.sorting;

import java.util.Arrays;

/**
 * @author kkv4all
 * @description 
 * 	Algorithm: Quick sort 
 * 		Quick Sort is a divide and conquer algorithm. It creates two empty arrays to hold elements less
 *	than the pivot value and elements greater than the pivot value, and then recursively sort the sub arrays. 
 *	There are two basic operations in the algorithm: 
 *		1. Partitioning a section of the array.
 *		2. Swapping items in place 
 *
 *	Time Complexity: Best Case - O(nLogn) , Worst Case - O(n^2)
 *	Space Complexity: O(nLogn)
 */
public class QuickSort {

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private int partition(int array[], int low, int high) {
		int pivot = array[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			// If current element is smaller than the pivot
			if (array[j] < pivot) {
				i++;
				swap(array, i, j);				
			}
		}

		swap(array, i+1, high);
		return i + 1;
	}

	public void sort(int[] array, int low, int high) {
		if (low < high) {
			int partitionIndex = partition(array, low, high);

			sort(array, low, partitionIndex - 1);
			sort(array, partitionIndex + 1, high);
		}
	}

	public static void main(String[] args) {
		int array[] = {10, 7, 8, 9, 1, 5}; 
		int n = array.length;

		QuickSort ob = new QuickSort(); 
		ob.sort(array, 0, n-1);

		Arrays.stream(array).forEach(i -> System.out.print(i+" "));
	}
}
