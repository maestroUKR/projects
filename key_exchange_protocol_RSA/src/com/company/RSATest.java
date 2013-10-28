package com.company;

import junit.framework.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class RSATest {
    @Test
    public void testGenerateKeyPair() throws Exception {
        RSA rsa = new RSA();
        rsa.generateKeyPair();

        Assert.assertEquals("Public key was not generated ... ", true, rsa.pubKey[0] instanceof BigInteger);
        Assert.assertEquals("Public key was not generated ... ", true, rsa.pubKey[1] instanceof BigInteger);

    }

    @Test
    public void testDecrypt() throws Exception {

        String message = "Hello, Alice!";
        RSA rsa = new RSA();
        rsa.generateKeyPair();
        BigInteger[] pKey = rsa.pubKey;
        BigInteger cipher = rsa.encrypt(message, pKey);
//        System.out.println("cipher = " + cipher.toString());
        String plain = rsa.decrypt(cipher, pKey);
//        System.out.println("plain = " + plain.toString());

        Assert.assertEquals("RSA is not work correctly ...", message, plain);

    }

    @Test
    public void testCreateSignature() throws Exception {
        RSA rsa = new RSA();
        rsa.generateKeyPair();
        String message = "Hello, Alice!";
        BigInteger signature = rsa.createSignature(message);

        boolean isVerify = rsa.verifySignature(message, signature, rsa.pubKey);

        Assert.assertEquals("Message is not original ... ", true, isVerify);
    }

    @Test
    public void testVerifySignature() throws Exception {
        RSA rsa = new RSA();
        rsa.generateKeyPair();
        String message = "Hello, Alice!";
        BigInteger signature = rsa.createSignature(message);

        boolean isVerify = rsa.verifySignature("Hello, Bob!", signature, rsa.pubKey);

        Assert.assertEquals("Message is not original ... ", false, isVerify);
    }
}
