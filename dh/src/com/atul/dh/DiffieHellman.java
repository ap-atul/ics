package com.atul.dh;

import java.math.BigInteger;

public class DiffieHellman {
    BigInteger p, g;
    public DiffieHellman(){}

    public void genPrimeAndPrimitiveRoot(){
        this.p = BigInteger.valueOf(new PrimeNumberGen().getPrimeNumber());
        this.g = BigInteger.valueOf(new PrimitiveRootGen(this.p.intValue()).getPr());
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getAliceMessage(BigInteger aliceSecretNumber){
        return this.g.modPow(aliceSecretNumber, this.p);
    }

    public BigInteger getBobMessage(BigInteger bobSecretNumber){
        return this.g.modPow(bobSecretNumber, this.p);
    }

    public BigInteger aliceCalculationOfKey(BigInteger bobMessage, BigInteger aliceSecretNumber){
        return bobMessage.modPow(aliceSecretNumber, this.p);
    }

    public BigInteger bobCalculationOfKey(BigInteger aliceMessage, BigInteger bobSecretNumber){
        return aliceMessage.modPow(bobSecretNumber, this.p);
    }
}
