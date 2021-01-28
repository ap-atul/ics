package com.atul.des;

import com.atul.util.Util;

public class Permutation {

	// 8 bits initial permutation
	private static final int[] IP = { 2, 6, 3, 1, 4, 8, 5, 7 };

	// 8 bits inverse initial permutation
	private static final int[] IP_ = { 4, 1, 3, 5, 7, 2, 8, 6 };

	// 10 bits permutation
	private static final int[] P10 = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6 };

	// 8 bits permutation
	private static final int[] P8 = { 6, 3, 7, 4, 8, 5, 10, 9 };

	// 4 bits permutation
	private static final int[] P4 = { 2, 4, 3, 1 };

	// 8 bits expansion permutation
	private static final int[] EP = { 4, 1, 2, 3, 2, 3, 4, 1 };
	
	// s0 box
	private static final int[][][] S0 = {
			{
				{0, 1}, {0, 0}, {1, 1}, {1, 0}
			},
			{
				{1, 1}, {1, 0}, {0, 1}, {0, 0}
			},
			{
				{0, 0}, {1, 0}, {0, 1}, {1, 1}
			},
			{
				{1, 1}, {0, 1}, {1, 1}, {1, 0}
			}
	};
	
	// s1 box
	private static final int[][][] S1 = {
			{
				{0, 0}, {0, 1}, {1, 0}, {1, 1}
			},
			{
				{1, 0}, {0, 0}, {0, 1}, {1, 1}
			},
			{
				{1, 1}, {0, 0}, {0, 1}, {0, 0}
			},
			{
				{1, 0}, {0, 1}, {0, 0}, {1, 1}
			}
	};

	/*
	 * Initial permutation on input array
	 */
	public static int[] initialPermutation(int[] arr) {
		int[] out = new int[IP.length];

		for (int i = 0; i < IP.length; i++) out[i] = arr[IP[i] - 1];
		return out;
	}
	
	/*
	 * Initial permutation on input array
	 */
	public static int[] initialPermutationInverse(int[] arr) {
		int[] out = new int[IP_.length];

		for (int i = 0; i < IP_.length; i++) out[i] = arr[IP_[i] - 1];
		return out;
	}
	
	/*
	 * Estimation permutation on input array
	 */
	public static int[] expansionPermutation(int[] arr) {
		int[] out = new int[EP.length];
		
		for(int i = 0; i < EP.length; i++) out[i] = arr[EP[i] - 1];
		return out;
	}
	
	/*
	 * Permutation 10 on the input array of size 10
	 */
	public static int[] permutation10(int[] arr) {
		int[] out = new int[P10.length];
		
		for(int i = 0; i < P10.length; i++) out[i] = arr[P10[i] - 1];
		return out;
	}
	
	/*
	 * Permutation 8 on the input array of size 10
	 */
	public static int[] permutation8(int[] arr) {
		int[] out = new int[P8.length];
		
		for(int i = 0; i < P8.length; i++) out[i] = arr[P8[i] - 1];
		return out;
	}
	
	/*
	 * Permutation 4 on the input array of size 10
	 */
	public static int[] permutation4(int[] arr) {
		int[] out = new int[P4.length];
		
		for(int i = 0; i < P4.length; i++) out[i] = arr[P4[i] - 1];
		return out;
	}

	/*
	 * Left shift array by d (distance)
	 */
	public static void shiftLeft(int[] arr, int d) {
		int temp[] = new int[d];

		for (int i = 0; i < d; i++)          temp[i] = arr[i];
		for (int i = d; i < arr.length; i++) arr[i - d] = arr[i];
		for (int i = 0; i < d; i++)          arr[i + arr.length - d] = temp[i];
	}
	
	/*
	 * Sbox 0 
	 */
	public static int[] sBox0(int[] arr) {
		int row = Util.binToDec(arr[0], arr[3]);
		int col = Util.binToDec(arr[1], arr[2]);
		return S0[row][col];
	}
	
	/**
	 * Sbox 1
	 */
	public static int[] sBox1(int[] arr) {
		int row = Util.binToDec(arr[0], arr[3]);
		int col = Util.binToDec(arr[1], arr[2]);
		return S1[row][col];
	}
}
