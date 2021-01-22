package com.atul.des;

import com.atul.util.Util;

public class DES {
	private int[] keyOne = null;
	private int[] keyTwo = null;

	public DES(String key) {
		int[] out = Util.strToInt(key);
		this.generateKeys(out);
	}

	private void generateKeys(int[] key) {
		if (key.length != 10) {
			System.out.println("Key of size 10 is required");
			return;
		}

		int[] keyPer = Permutation.permutation10(key);

		// creating k1
		int half = keyPer.length / 2;
		int[] halveOne = new int[half];
		int[] halveTwo = new int[half];
		Util.split(keyPer, halveOne, halveTwo);
		Permutation.shiftLeft(halveOne, 1);
		Permutation.shiftLeft(halveTwo, 1);
		keyOne = Util.combine(halveOne, halveTwo);
		keyOne = Permutation.permutation8(keyOne);

		// creating k1
		half = keyPer.length / 2;
		halveOne = new int[half];
		halveTwo = new int[half];
		Util.split(keyPer, halveOne, halveTwo);
		Permutation.shiftLeft(halveOne, 3);
		Permutation.shiftLeft(halveTwo, 3); // use prev & shift only 2
		keyTwo = Util.combine(halveOne, halveTwo);
		keyTwo = Permutation.permutation8(keyTwo);

//		System.out.println("Key one");
//		Util.print(keyOne);
//		
//		System.out.println("Key two");
//		Util.print(keyTwo);
	}

	public String encrypt(String message) {
		int[] block = Util.strToInt(message);
		return Util.intToStr(encryptBlock(block));
	}

	public String decrypt(String message) {
		int[] block = Util.strToInt(message);
		return Util.intToStr(decryptBlock(block));
	}

	public int[] encryptBlock(int[] block) {
		int[][] keys = { this.keyOne, this.keyTwo };
		block = Operations.run(Permutation.initialPermutation(block), keys);
		return Permutation.initialPermutationInverse(block);
	}

	public int[] decryptBlock(int[] block) {
		int[][] keys = { this.keyTwo, this.keyOne };
		block = Operations.run(Permutation.initialPermutation(block), keys);
		return Permutation.initialPermutationInverse(block);
	}
}
