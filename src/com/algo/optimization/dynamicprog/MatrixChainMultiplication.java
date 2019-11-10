package com.algo.optimization.dynamicprog;

/**
 * @author kkv4all
 * @description
 * 	Algorithm: Optimization Problem -> Dynamic Programming -> Matrix Chain Multiplication
 * 		Given a sequence of matrices.
 * 		We need to find the most efficient way to multiply these matrices together. 
 * 		We need to decide in which order to perform the multiplications.
 * 
 */

public class MatrixChainMultiplication {

	public static void main(String[] args) {
		int[] dimensions = {5,4,6,2,7};
		MatrixChainMultiplication matrixChainMultiplication = new MatrixChainMultiplication();

		int minimumNumberOfMultiplication = matrixChainMultiplication.getMinimumNumberOfMultiplication(dimensions);

		System.out.println(minimumNumberOfMultiplication);
	}

	public int getMinimumNumberOfMultiplication(int[] dimensions) {
		int n = dimensions.length;
		int[][] multiplication = new int[n][n];
		int[][] s = new int [n][n];

		for(int d=0; d<n-1; d++) {
			for(int i=1; i<n-d; i++) {
				int j = i + d;
				int minimumMultiples = Integer.MAX_VALUE;
				for(int k=i; k<=j-1;k++) {
					int multiples = multiplication[i][k] + multiplication[k+1][j] + (dimensions[i-1]*dimensions[k]*dimensions[j]);
					if(multiples < minimumMultiples) {
						minimumMultiples = multiples;
						s[i][j] = k;
					}
					multiplication[i][j] = minimumMultiples;
				}
			}
		}
		return multiplication[1][n-1];
	}
}
