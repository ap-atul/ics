package com.atul.des;

import com.atul.util.Util;

public class Operations {

	public static int[] run(int[] block, int[][] keys) {
		int[] right = new int[block.length / 2];
		int[] left = new int[block.length / 2];
		Util.split(block, left, right);

		for (int i = 0; i < keys.length; i++) {
			int[] xor = Util.xor(keys[i], Permutation.expansionPermutation(right));

			int[] halveOne = new int[xor.length / 2];
			int[] halveTwo = new int[xor.length / 2];
			Util.split(xor, halveOne, halveTwo);

			int[] sbox0 = Permutation.sBox0(halveOne);
			int[] sbox1 = Permutation.sBox1(halveTwo);
			left = Util.xor(Permutation.permutation4(Util.combine(sbox0, sbox1)), left);

			if (i == 0) {
				int[] temp = left;
				left = right;
				right = temp;
			}
		}

		return Util.combine(left, right);
	}
}
