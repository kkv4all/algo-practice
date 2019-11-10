package com.algo.optimization.dynamicprog;

/**
 * @author kkv4all
 * @description
 * 	Algorithm: Optimization Problem -> Dynamic Programming -> Longest Common Subsequence
 * 		Given two character sequences.
 * 		we need to find the length of longest subsequence present in both of them. 
 * 		A subsequence should be a sequence that appears in the same relative order, but not necessarily contiguous.
 * 
 */

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		String first = "AGGTAB";
		String second = "GXTXAYB";
		LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();

		String longestSubsequence = longestCommonSubsequence.dynamicSolution(first,second);
		System.out.println("Longest Common Subsequence for '" + first + "' and '" + second + "' is '" + longestSubsequence + "'");
	}

	public String dynamicSolution(String first, String second) {
		StringBuilder longestSubsequence = new StringBuilder();
		int[][] lcsTable = getLCSTable(first.toCharArray(),second.toCharArray(),first.length(),second.length());

		int firstIndex = first.length();
		int secondIndex = second.length();
		while(firstIndex>0 && secondIndex>0) {
			if(lcsTable[firstIndex][secondIndex] == lcsTable[firstIndex-1][secondIndex]) {
				firstIndex --;
			} else if(lcsTable[firstIndex][secondIndex] == lcsTable[firstIndex][secondIndex-1]) {
				secondIndex --;
			} else {
				longestSubsequence.insert(0, second.charAt(secondIndex-1));
				firstIndex--;
				secondIndex--;
			}
		}
		return longestSubsequence.toString();
	}

	public int[][] getLCSTable(char[] X, char[] Y, int m, int n) {
		int LCS[][] = new int[m+1][n+1]; 

		for (int i=0; i<=m; i++) 
		{ 
			for (int j=0; j<=n; j++) 
			{ 
				if (i == 0 || j == 0) 
					LCS[i][j] = 0; 
				else if (X[i-1] == Y[j-1]) 
					LCS[i][j] = LCS[i-1][j-1] + 1; 
				else
					LCS[i][j] = max(LCS[i-1][j], LCS[i][j-1]); 
			} 
		} 
		return LCS;
	}

	private int max(int i, int j) {
		return (i > j)? i : j;
	}
}
