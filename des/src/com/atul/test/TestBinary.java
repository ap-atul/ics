package com.atul.test;

import com.atul.des.DES;

public class TestBinary {

	public static void main(String[] args) {
		String message = "00101000";
		String key = "1100011110";
		System.out.println("Original Message :: " + message);

		DES des = new DES(key);
		String cipherText = des.encrypt(message);

		System.out.println("Cipher Message :: " + cipherText);
		String decryptedText = des.decrypt(cipherText);

		System.out.println("Decrypted Message :: " + decryptedText);
	}
	
}
