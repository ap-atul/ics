package com.atul.dh;

import java.math.BigInteger;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		DiffieHellman dh = new DiffieHellman();
		Scanner scan = new Scanner(System.in);
		
		dh.genPrimeAndPrimitiveRoot();
		
		System.out.println("\nGenerating prime and primitive root ...");
        System.out.println("Prime:: " + dh.getP().toString());
        System.out.println("Primitive Root:: " + dh.getG().toString());
        
        System.out.println("\nEnter the secret messages of alice and bob:: ");
        BigInteger aliceSecVal = BigInteger.valueOf(Long.parseLong(scan.next()));
        BigInteger bobSecVal = BigInteger.valueOf(Long.parseLong(scan.next()));
        
        BigInteger aliceMsgVal = dh.getAliceMessage(aliceSecVal);
        BigInteger bobMsgVal = dh.getBobMessage(bobSecVal);
        
        System.out.println("\nInteger values of the messages:: ");
        System.out.println("Alice:: " + aliceMsgVal.toString());
        System.out.println("Bob:: " + bobMsgVal.toString());
        
        System.out.println("\nKeys for the communication :: ");
        System.out.println("Alice:: " + dh.aliceCalculationOfKey(bobMsgVal, aliceSecVal).toString());
        System.out.println("Bob:: " + dh.bobCalculationOfKey(aliceMsgVal, bobSecVal).toString());
	}
}
