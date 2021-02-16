package com.atul.aes;

public class SBox {

	public static String[] sbox = { 
			"1001", "0100", "1010", "1011",
			"1101", "0001", "1000", "0101",
			"0110", "0010", "0000", "0011",
			"1100", "1110", "1111", "0111"
			};

	public static String[] sbox_inverse = { 
			"1010", "0101", "1001", "1011",
			"0001", "0111", "1000", "1111",
			"0110", "0000", "0010", "0011",
			"1100", "0100", "1101", "1110"
			};

	public static String nibbleSub(String key) {
		String s1 = sbox[Integer.parseInt(key.substring(0, 4), 2)];
		String s2 = sbox[Integer.parseInt(key.substring(4, 8), 2)];
		return s1 + s2;
	}

	public static String inverseNibbleSub(String key) {
		String s1 = sbox_inverse[Integer.parseInt(key.substring(0, 4), 2)];
		String s2 = sbox_inverse[Integer.parseInt(key.substring(4, 8), 2)];
		return s1 + s2;
	}

	public static String rotateNibble(String key) {
		String s1 = key.substring(0, 4);
		String s2 = key.substring(4, 8);
		return s2 + s1;
	}
}
