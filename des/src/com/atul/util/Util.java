package com.atul.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Util {
	public static String utfToBin(String input) {
		String bin = "";
		byte[] bytesData = null;

		try {
			bytesData = input.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < bytesData.length; i++) {
			int val = bytesData[i];

			// byte 1 = 8 bits
			for (int j = 0; j < 8; j++) {
				bin += ((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		
		return bin;
	}

	public static String binToUtf(String bin) {
		String utf = "";
		
		int remainder = bin.length() % 8;
		// padding
		if (remainder != 0) {
			for (int i = 0; i < (8 - remainder); i++)
				bin = "0" + bin;
		}
		
		for (int index = 0; index < bin.length(); index += 8) {
			String temp = bin.substring(index, index + 8);
			int num = Integer.parseInt(temp, 2);
			char letter = (char) num;
			utf += letter;
		}

		return utf;
	}

	public static char[] strToChar(String bin) {
		return bin.toCharArray();
	}

	public static int[] strToInt(String bin) {
		int[] array = new int[bin.length()];

		for (int i = 0; i < bin.length(); i++) {
			array[i] = Integer.parseInt(bin.substring(i, i + 1));
		}

		return array;
	}

	public static void print(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	public static void print(char[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	public static void print(byte[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int val = arr[i];

			// byte 1 = 8 bits
			for (int j = 0; j < 8; j++) {
				System.out.print((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
	}

	/**
	 * Split into left and right
	 */
	public static void split(int[] input, int[] left, int[] right) {
		int half = input.length / 2;

		for (int i = 0; i < half; i++)
			left[i] = input[i];
		for (int i = half, j = 0; i < input.length; i++, j++)
			right[j] = input[i];
	}

	/**
	 * Combine left and right
	 */
	public static int[] combine(int[] left, int[] right) {
		int[] out = new int[left.length + right.length];

		for (int i = 0; i < left.length; i++)
			out[i] = left[i];
		for (int i = 0, j = left.length; i < right.length; i++, j++)
			out[j] = right[i];

		return out;
	}

	/**
	 * XOR of two arrays
	 */
	public static int[] xor(int[] one, int[] two) {
		int[] out = new int[one.length];

		for (int i = 0; i < one.length; i++)
			out[i] = one[i] ^ two[i];

		return out;
	}

	/**
	 * Swap two arrays
	 */
	public static void swap(int[] one, int[] two) {
		int[] temp = one;
		one = two;
		two = temp;
	}

	/**
	 * Return decimal for two bit
	 */
	public static int binToDec(int b1, int b2) {
		if (b1 == 0 && b2 == 0)
			return 0;
		if (b1 == 0 && b2 == 1)
			return 1;
		if (b1 == 1 && b2 == 0)
			return 2;
		if (b1 == 1 && b2 == 1)
			return 3;
		return -1;
	}

	/**
	 * Return string from arr
	 */
	public static String intToStr(int[] arr) {
		String s = "";

		for (int i = 0; i < arr.length; i++)
			s += String.valueOf(arr[i]);

		return s;
	}

	/**
	 * Create separate blocks
	 */
	public static String[] splitBlocks(String block, int size) {
		int remainder = block.length() % size;
		// padding
		if (remainder != 0) {
			for (int i = 0; i < (size - remainder); i++)
				block = "0" + block;
		}

		String[] out = new String[block.length() / size];
		int offset = 0;
		for (int i = 0; i < out.length; i++) {
			out[i] = block.substring(offset, offset + size);
			offset += size;
		}

		return out;
	}

	/*
	 * Combine blocks
	 */
	public static String mergeBlocks(String[] blocks) {
		String out = "";

		for (int i = 0; i < blocks.length; i++)
			out += blocks[i];

		return out;
	}
}
