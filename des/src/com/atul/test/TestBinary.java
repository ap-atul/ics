package com.atul.test;

import com.atul.des.DES;

public class TestBinary {

	public static void main(String[] args) {		
		String message = "This is a very secure encryption format"
				+ " and its impossible to break with my secret password."
				+ " Best of luck to hackers.";
		String key = "1010110101";
		
		DES des = new DES(key);
		String enc = des.encrypt(message);
		String dec = des.decrypt(enc);
		
		
		System.out.println("Original message :: " + message);
		System.out.println("Encrypted message :: " + enc);
		System.out.println("Decrypted message :: " + dec);
	}
	
}
