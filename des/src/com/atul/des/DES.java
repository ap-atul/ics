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
		int[][] keys = Operations.keygen(keyPer, 2);
		
		this.keyOne = keys[0];
		this.keyTwo = keys[1];
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
