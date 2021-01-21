package com.atul.des;

import com.atul.util.Util;
import com.atul.*;

public class DES {
	private int[] keyOne = null;
	private int[] keyTwo = null;
	
	public DES(String key) {
		int[] out = Util.strToInt(key);
		this.generateKeys(out);
	}
	
	private void generateKeys(int[] key) {
		if(key.length != 10) {
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
		
		System.out.println("Key one");
		Util.print(keyOne);
		
		System.out.println("Key two");
		Util.print(keyTwo);
		System.out.println();
	}

	public String encrypt(String message) {
		int[] ip = Permutation.initialPermutation(Util.strToInt(message));
		System.out.println("Initial Permutation");
		Util.print(ip);
		
		// split
		int half = ip.length / 2;
		int[] leftHalve = new int[half];
		int[] rightHalve = new int[half];
		Util.split(ip, leftHalve, rightHalve);
		System.out.println("\n Halves");
		Util.print(leftHalve);
		Util.print(rightHalve);
		
		// expanding right and xor with key
		int[] rightEx = Permutation.expansionPermutation(rightHalve);
		System.out.println("\nExpansion Permutation");
		Util.print(rightEx);
		
		int[] xor = Util.xor(this.keyOne, rightEx);
		System.out.println("\nXor ");
		Util.print(xor);

		// split
		half = ip.length / 2;
		int[] halveOne = new int[half];
		int[] halveTwo = new int[half];
		Util.split(xor, halveOne, halveTwo);
		
		int[] sbox0 = Permutation.sBox0(halveOne);
		int[] sbox1 = Permutation.sBox1(halveTwo);
		System.out.println("\nSboxes ");
		Util.print(sbox0);
		Util.print(sbox1);
		
		int[] p4 = Permutation.permutation4(Util.combine(sbox0, sbox1));
		System.out.println("\nPermutation 4");
		Util.print(p4);
		
		leftHalve = Util.xor(p4, leftHalve);
		System.out.println("\nHalves after Round 0");
		Util.print(leftHalve);
		Util.print(rightHalve);
		
		// ******** ROUND 2 ***************8
		int[] right = leftHalve;
		int[] left = rightHalve;
		System.out.println("\nHalves swapped");
		Util.print(left);
		Util.print(right);
		
		// expanding right and xor with key
		rightEx = Permutation.expansionPermutation(right);
		System.out.println("\nExpansion Permutation");
		Util.print(rightEx);
		
		xor = Util.xor(this.keyTwo, rightEx);
		System.out.println("\nXor ");
		Util.print(xor);

		// split
		half = ip.length / 2;
		halveOne = new int[half];
		halveTwo = new int[half];
		Util.split(xor, halveOne, halveTwo);
		
		sbox0 = Permutation.sBox0(halveOne);
		sbox1 = Permutation.sBox1(halveTwo);
		System.out.println("\nSboxes ");
		Util.print(sbox0);
		Util.print(sbox1);
		
		p4 = Permutation.permutation4(Util.combine(sbox0, sbox1));
		System.out.println("\nPermutation 4");
		Util.print(p4);
		
		left = Util.xor(p4, left);
		System.out.println("\nHalves after Round 1");
		Util.print(left);
		Util.print(right);
		
		int[] out = Util.combine(left, right);
		out = Permutation.initialPermutationInverse(out);
		System.out.println("\nAfter Round two, cipher text");
		Util.print(out);
		
		return Util.intToStr(out);
	}

	public String decrypt(String message) {
		return "";
	}

	public static void main(String[] args) {
		DES des = new DES("1010000010");
		String cipherText = des.encrypt("01110010");
		System.out.println("\n" + cipherText);
	}
}
