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

		int[] shifts = {1, 3};
		int[] keyPer = Permutation.permutation10(key);
		int[][] keys = Operations.keygen(keyPer, shifts, 2);
		
		this.keyOne = keys[0];
		this.keyTwo = keys[1];
	}

	public String encrypt(String message) {
		message = Util.utfToBin(message);
		String[] blocks = Util.splitBlocks(message, 8);
		String[] encrypted = new String[blocks.length];
		
		for(int i = 0; i < blocks.length; i++) {
			int[] enc = encryptBlock(Util.strToInt(blocks[i]));
			encrypted[i] = Util.intToStr(enc);
		}

		return Util.mergeBlocks(encrypted);
	}

	public String decrypt(String bin) {
		String[] blocks = Util.splitBlocks(bin, 8);
		String[] decrypted = new String[blocks.length];
		
		for(int i = 0; i < blocks.length; i++) {
			int[] enc = decryptBlock(Util.strToInt(blocks[i]));
			decrypted[i] = Util.intToStr(enc);
		}
		
		return Util.binToUtf(Util.mergeBlocks(decrypted));
	}

	private int[] encryptBlock(int[] block) {
		int[][] keys = { this.keyOne, this.keyTwo };
		block = Operations.run(Permutation.initialPermutation(block), keys, 2);
		return Permutation.initialPermutationInverse(block);
	}

	private int[] decryptBlock(int[] block) {
		int[][] keys = { this.keyTwo, this.keyOne };
		block = Operations.run(Permutation.initialPermutation(block), keys, 2);
		return Permutation.initialPermutationInverse(block);
	}
}
