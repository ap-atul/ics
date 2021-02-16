package com.atul.aes;

public class Util {
	public static String shiftRows(String string) {
		String s1 = string.substring(0, 4);
		String s2 = string.substring(4, 8);
		String s3 = string.substring(8, 12);
		String s4 = string.substring(12, 16);
		return s1 + s4 + s3 + s2;
	}

	public static String mixColumn(String str) {
		String s00 = xor(str.substring(0, 4), multiplication("0100", str.substring(8, 12)));
		String s10 = xor(str.substring(8, 12), multiplication("0100", str.substring(0, 4)));
		String s01 = xor(str.substring(4, 8), multiplication("0100", str.substring(12, 16)));
		String s11 = xor(str.substring(12, 16), multiplication("0100", str.substring(4, 8)));
		return s00 + s10 + s01 + s11;
	}

	public static String multiplication(String s1, String s2) {
		int t1 = Integer.parseInt(s1, 2);
		int t2 = Integer.parseInt(s2, 2);
		int p = 0;

		while (t2 > 0) {
			if ((t2 & 0b1) != 0) {
				p ^= t1;
			}

			t1 <<= 1;
			if (((t1 & 0b10000) != 0))
				t1 ^= 0b11;
			t2 >>= 1;
		}
		int val = p & 0b1111;

		String ans = "";
		if (Integer.toBinaryString(val).length() < 4) {
			int temp = 4 - Integer.toBinaryString(val).length();
			for (int i = 0; i < temp; i++) {
				ans = ans + "0";

			}
			ans = ans + Integer.toBinaryString(val);
			return ans;

		} else
			return Integer.toBinaryString(val);

	}

	public static String xor(String a, String b) {
		String xor = "";
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(i))
				xor = xor + "0";
			else
				xor = xor + "1";
		}
		return xor;
	}
}
