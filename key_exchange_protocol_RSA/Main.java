/*package com.company;*/

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        /**
         *  Part I. Two-part communication: encrypt/decrypt messages;
         *  create/verify digital signature
         */

        String message = "Hello, I'm Alice!";
        // Alice's private and public key
        RSA rsaAlice = new RSA();
        rsaAlice.generateKeyPair();
        // Bob's private and public key
        RSA rsaBob = new RSA();
        rsaBob.generateKeyPair();
        // encrypted message from Alice to Bob
        BigInteger cipher = rsaAlice.encrypt(message, rsaBob.pubKey);
        // create digital signature by Alice
        BigInteger signature = rsaAlice.createSignature(message);

        // Bob decrypt message and verify signature
        String plain = rsaBob.decrypt(cipher, rsaBob.pubKey);
        boolean isVerify = rsaBob.verifySignature(plain, signature, rsaAlice.pubKey);

        if( isVerify )
        {
            System.out.println("Plain text : " + plain);
        }

        /**
         *  Part II. Two-part RSA protocol to receive
         *  session key, with signature
         */

        // A's private and public key
        RSA rsaA = new RSA();
        rsaA.generateKeyPair();
        // B's private and public key
        RSA rsaB = new RSA();
        rsaB.generateKeyPair();

        BigInteger newSessionKey = new BigInteger("123456789123465", 10);
        // create message by A
        BigInteger S = newSessionKey.modPow(rsaA.getPrivKeyD(), rsaA.pubKey[1]);
        BigInteger k1 = newSessionKey.modPow(rsaB.pubKey[0], rsaB.pubKey[1]);
        BigInteger S1 =  S.modPow(rsaB.pubKey[0], rsaB.pubKey[1]);

        // --------- sending message to B --------- >>>

        // receive message by B and take new session key
        BigInteger k = k1.modPow(rsaB.getPrivKeyD(), rsaB.pubKey[1]);
        BigInteger Sign = S1.modPow(rsaB.getPrivKeyD(), rsaB.pubKey[1]);
        BigInteger verifyK = Sign.modPow(rsaA.pubKey[0], rsaA.pubKey[1]);

        if(verifyK.equals(k))
        {
            System.out.println("B receive new session key from A: " + k.toString());
        }
        else
        {
            System.out.println("B receive FAKE session key from A: " + k.toString());
        }

    }
}
