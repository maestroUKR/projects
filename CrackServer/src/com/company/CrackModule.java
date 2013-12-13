package com.company;


import java.math.BigInteger;
import java.util.Random;

public class CrackModule {

    private BigInteger t;

    /**
     * generate number by modulo
     * @param module
     */
    public void generateT(BigInteger module)
    {
        Random rnd = new Random();
        t = new BigInteger(128, rnd).mod(module);
    }

    /**
     * return generated number
     * @return
     */
    public BigInteger getSupposeT()
    {
        return t;
    }

    /**
     * return t^2 mod n
     * @param module
     * @return
     */
    public BigInteger getSqrT(BigInteger module)
    {
        return t.modPow(new BigInteger("2"), module);
    }

    /**
     * check is  t!=z and compute p,q where p*q == n
     * @param z
     * @param n
     * @return
     */
    public boolean isFind_P_Q(BigInteger z, BigInteger n)
    {
        if(t.equals(z))
        {
            System.out.print(" t == z \n");
            return false;
        }
        else
        {
            BigInteger p =  n.gcd(t.add(z));

            if(p.equals(BigInteger.ONE) || p.equals(n))
            {
                System.out.print("  (n, t +z) = 1 \n");
                return false;
            }

            System.out.print("p = " + p.toString() + "\n");
            System.out.print("q = " + n.divide(p).toString() + "\n");

            if(p.multiply(n.divide(p)).equals(n))
                System.out.print("\nCheck ... p*q = n \n");
        }
        return true;
    }
}
