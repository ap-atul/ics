package com.atul.aes;

public class AES {
	private String[] W = null;

	public AES(String key) {
		W = new String[6];
		
		W[0] = key.substring(0, 8);
		W[1] = key.substring(8, 16);
		W[2] = Util.xor(Util.xor(W[0], "10000000"), SBox.nibbleSub(SBox.rotateNibble(W[1])));
		W[3] = Util.xor(W[2], W[1]);
		W[4] = Util.xor(Util.xor(W[2], "00110000"), SBox.nibbleSub(SBox.rotateNibble(W[3])));
		W[5] = Util.xor(W[4], W[3]);
	}

	public String encrypt(String plain) {
		String operations = Util.xor(plain, W[0] + W[1]);
		operations = (SBox.nibbleSub(operations.substring(0, 8)) + SBox.nibbleSub(operations.substring(8, 16)));
		operations = Util.mixColumn(Util.shiftRows(operations));
		operations = Util.xor(operations, W[2] + W[3]);
		operations = (SBox.nibbleSub(operations.substring(0, 8)) + SBox.nibbleSub(operations.substring(8, 16)));
		String cipher = Util.xor(Util.shiftRows(operations), W[4] + W[5]);

		return cipher;
	}

	public String decrypt(String cipher) {
		String operations = Util.xor(cipher, W[4] + W[5]);
		operations = Util.shiftRows(operations);
		operations = (
				SBox.inverseNibbleSub(operations.substring(0, 8)) +
				SBox.inverseNibbleSub(operations.substring(8, 16))
				);
		operations = Util.xor(operations, W[2] + W[3]);
		String rot = SBox.rotateNibble(operations.substring(4, 12));
		operations = operations.substring(0, 4) + rot + operations.substring(12, 16);
		operations = Util.inverseMixColumn(operations);
		operations = Util.shiftRows(operations);
		operations = (
				SBox.inverseNibbleSub(operations.substring(0, 8)) +
				SBox.inverseNibbleSub(operations.substring(8, 16))
				);
		String plain = Util.xor(operations, W[0] + W[1]);
		return plain;
	}

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
