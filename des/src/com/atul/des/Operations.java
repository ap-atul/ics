package com.atul.des;

import com.atul.util.Util;

public class Operations {

	/*
	 * Perform the set of operations on the block of binary data.
	 */
	public static int[] run(int[] block, int[][] keys, int rounds) {
		int[] right = new int[block.length / 2];
		int[] left = new int[block.length / 2];
		Util.split(block, left, right);

		for (int i = 0; i < rounds; i++) {
			int[] xor = Util.xor(keys[i], Permutation.expansionPermutation(right));

			int[] halveOne = new int[xor.length / 2];
			int[] halveTwo = new int[xor.length / 2];
			Util.split(xor, halveOne, halveTwo);

			int[] sbox0 = Permutation.sBox0(halveOne);
			int[] sbox1 = Permutation.sBox1(halveTwo);
			left = Util.xor(Permutation.permutation4(Util.combine(sbox0, sbox1)), left);

			if (i < rounds - 1) { // final round no swaps
				int[] temp = left;
				left = right;
				right = temp;
			}
		}

		return Util.combine(left, right);
	}

	/*
	 * Generate two keys for the SDES
	 */
	public static int[][] keygen(int[] key, int[] shifts, int no) {
		int[][] keys = new int[key.length][key.length];

		for (int i = 0; i < no; i++) {
			int half = key.length / 2;
			int[] halveOne = new int[half];
			int[] halveTwo = new int[half];
			Util.split(key, halveOne, halveTwo);
			
			Permutation.shiftLeft(halveOne, shifts[i]);
			Permutation.shiftLeft(halveTwo, shifts[i]);

			keys[i] = Permutation.permutation8(Util.combine(halveOne, halveTwo));
		}

		return keys;
	}
}
