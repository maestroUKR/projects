package com.company;

import java.lang.Math;
import java.util.Random;
import java.math.BigInteger;

public class MillerRabbinTest {

    public static BigInteger d = new BigInteger("0", 10);
    public static int s = 0;
    public static BigInteger[] smallPrimes = {new BigInteger("2", 10), new BigInteger("3", 10),
                               new BigInteger("5", 10), new BigInteger("7", 10), new BigInteger("11", 10)};

    /**
     *
     * @param p - number to verifying on prime
     * @return  true(prime) or false(not prime)
     */
    public static boolean computeTest(BigInteger p)
    {
        BigInteger temp = p.subtract(new BigInteger("1", 10));
        BigInteger TWO = new BigInteger("2", 10);
        BigInteger ZERO = new BigInteger("0", 10);
        /**
         * Faster verifying on composite value
         */
        for(BigInteger bi : smallPrimes)
        {
            if(p.mod(bi).equals(ZERO))
            {
                return false;
            }
        }
        /**
         *  start value of 's' and 'd'
         */
     d = new BigInteger("0", 10);
     s = 0;

     Random rand = new Random();
        /**
         *  k is a limit value for count, so
         *  use length of BigInteger to determine
         *  this max value
         */
     int k = Math.abs(rand.nextInt(32*p.bitLength()));
     if (k == 0)
         k = Math.abs(rand.nextInt(64*p.bitLength()));

     int count = 0;
     /**
     *   view p-1 = d * (2^s)
     */

      while(temp.remainder(TWO).equals(ZERO))
      {
          s++;
          temp = temp.divide(TWO);
      }
        d = (p.subtract(new BigInteger("1", 10))).divide(new BigInteger(String.valueOf((int) Math.pow(2, s)), 10));

        /**
         *  Miller-Rabbin test algorithm for prime
         */
      while(count < k)
      {
          BigInteger x = new BigInteger(String.valueOf((int) rand.nextInt((int) Math.pow(10, p.toString().length() - 1))), 10);

          while (x.compareTo(p) != -1)
          {
              x = new BigInteger(String.valueOf((int) rand.nextInt((int) Math.pow(10, p.toString().length() - 1))), 10);
          }
          while(x.compareTo(ZERO) == 0)
          {
              x = new BigInteger(String.valueOf((int) rand.nextInt((int) Math.pow(10, p.toString().length() - 1))), 10);
          }

          if(!gcd(x, p).equals(new BigInteger("1", 10)))
          {
              return false;
          }

          if(!isStrongPseudoPrime(p, x, d, s))
          {
              return false;
          }

          count++;
      }

       return true;
    }

    /**
     *   Euclid algorithm
     */
    public static BigInteger gcd(BigInteger p, BigInteger q)
    {
        return p.gcd(q);
    }

    /**
     *
     * @param p - number (maybe prime ?)
     * @param base  - is p strong pseudo prime number by this base
     * @param d  - p - 1 = d*(2^s)
     * @param s  - p - 1 = d*(2^s)
     * @return   true(is strong pseudo prime) or false(not is strong pseudo prime)
     */
    public static boolean isStrongPseudoPrime(BigInteger p, BigInteger base, BigInteger d, int s)
    {
        BigInteger ONE = new BigInteger("1", 10);
        BigInteger TWO = new BigInteger("2", 10);

       if(base.modPow(d, p).compareTo(ONE) == 0  || base.modPow(d, p).compareTo(p.subtract(ONE)) == 0)
       {
           return true;
       }
       else
       {
           BigInteger x = base.modPow(d, p);

           for(int r = 1; r < s; ++r)
           {
             x = x.modPow(TWO, p);

             if(x.equals(p.subtract(ONE)))
                 return true;
             if(x.equals(ONE) )
                 return false;
           }
       }
       return false;
    }
}
