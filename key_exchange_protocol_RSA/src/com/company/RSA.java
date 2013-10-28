package com.company;

import java.math.BigInteger;
import java.util.Random;


public class RSA {
    private BigInteger[] privKey = new BigInteger[3];

    public BigInteger[] pubKey = new  BigInteger[2];

    /**
     *
     * @return - RSA private key d, which need
     *  in digital signature
     */
    public final BigInteger getPrivKeyD()
    {
        return privKey[0];
    }

    /**
     *  function to generate public and private key of RSA
     */
    public void generateKeyPair()
    {
        BigInteger ONE = new BigInteger("1", 10);

        JavaNumberGenerator jng = new JavaNumberGenerator(256);
        BigInteger pq[] = jng.generate2PrimesNumber();

        /*
            n = p*q
         */
        BigInteger n = pq[0].multiply(pq[1]);
        /*
            fi(n) = (p-1)*(q-1)
         */
        BigInteger fi = (pq[0].subtract(ONE)).multiply(pq[1].subtract(ONE));

        /*
            generate public key e, where gcd(x, fi) == 1
         */
        Random random = new Random();
        BigInteger e = ONE, d = ONE;
        try{
            e = new BigInteger(String.valueOf(random.nextInt()), 10).abs();
        while(e.compareTo(fi.subtract(ONE)) != -1 | MillerRabbinTest.gcd(e,fi).equals(ONE) == false |
                MillerRabbinTest.computeTest(e) == false)
        {
            e = new BigInteger(String.valueOf(random.nextInt()), 10).abs();
        }

             d = e.modInverse(fi).abs();
        }
        catch  (ArithmeticException ex)
        {
            System.out.println("Catch exception: " + ex.toString());

        }
        finally {
            System.out.println("e = : " + e.toString());
            System.out.println("d = : " + d.toString());
            System.out.println("fi = : " + fi.toString());
            System.out.println("n = : " + n.toString());
        }

        privKey[0] = d;
        privKey[1] = pq[0];
        privKey[2] = pq[1];

        pubKey[0] = e;
        pubKey[1] = n;

    }

    /**
     *
     * @param message - input data to encrypt
     * @param publicKey - RSA public Key e
     * @return  cipher text
     */
    public BigInteger encrypt(String message, BigInteger[] publicKey)
    {
        BigInteger msg = new BigInteger(message.getBytes());

        if(msg.compareTo(publicKey[1]) != -1)
        {
            System.out.println("Message must be less than modulo! Error encryption");
        }
        else
        {
            BigInteger cipher = msg.modPow(publicKey[0], publicKey[1]);

            return cipher;
        }

        return BigInteger.ONE;
    }

    /**
     *
     * @param cipher  cipher text, encrypted by RSA
     * @param publicKey public key of RSA
     * @return decrypted data
     */
    public String decrypt(BigInteger cipher, BigInteger[] publicKey)
    {
        if(cipher.compareTo(publicKey[1].subtract(BigInteger.ONE)) != -1)
        {
            System.out.println("Cipher must be less than modulo! Error decryption");
        }
        else
        {
           BigInteger message = cipher.modPow(privKey[0], publicKey[1]);

           String plainText = new String(message.toByteArray());

           return plainText;
        }

        return "";
    }

    /**
     *
     * @param message  plain data
     * @return  digital signature od message
     */
    public BigInteger createSignature(String message)
    {
        BigInteger signature =  new BigInteger(message.getBytes()).modPow(privKey[0], pubKey[1]);

        return signature;
    }

    /**
     *
     * @param msg receive data
     * @param originSignature receive digital signature
     * @param publicKey RSA public Key
     * @return  true if signature are equal and false if not equal
     */
    public boolean verifySignature(String msg, BigInteger originSignature, BigInteger[] publicKey)
    {
       String computeMsg = new String(originSignature.modPow(publicKey[0], publicKey[1]).toByteArray());

       if(!msg.equals(computeMsg))
       {
           return false;
       }
        else
       {
           return true;
       }
    }
}
