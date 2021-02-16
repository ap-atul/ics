package com.atul.aes;

public class Test {
	public static void main(String args[]) {
		String plain = "1010101010101010";
		String key = "1010101010101010";
		AES aes = new AES(key);
		String cipher = aes.encrypt(plain);
		
		System.out.println(plain);
		System.out.println(cipher);
		System.out.println(aes.decrypt(cipher));
	}
}
