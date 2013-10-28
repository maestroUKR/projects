package com.company;

import java.util.Random;
import java.math.BigInteger;

public class JavaNumberGenerator {
    private int length;

    /**
     *
     * @param length - set a length of generated number
     */
    public JavaNumberGenerator(int length)
    {
        this.length = length;
    }

    /**
     *
     * @return  - generated number, which can be a prime
     */
    public BigInteger generateRandom()
    {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        BigInteger TWO = new BigInteger("2", 10);
        for(int i = 0; i < length; ++i)
        {
          int k =  random.nextInt()%2;
          sb.append(Math.abs(k));
        }
        BigInteger prn = TWO;
        try
        {
            prn = new BigInteger(sb.toString(), 2);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Exception: " + e.toString());
            System.out.println("Exception: " + sb.toString());
        }
        return prn;
    }

    /**
     *
     * @return  -- two primes number with length in constructor
     */
    public BigInteger[] generate2PrimesNumber()
    {
        BigInteger big2Primes[] = new BigInteger[2];
        BigInteger ONE = new BigInteger("1", 10);
        BigInteger TWO = new BigInteger("2", 10);
        BigInteger p = ONE,q = ONE, p1, q1;

        try{
            p = generateRandom();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Generate exception: " + e.toString());
        }
        while (!MillerRabbinTest.computeTest(p))
        {
            try{
                p = generateRandom();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Generate exception: " + e.toString());
            }
        }
        try{
            q = generateRandom().abs();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Generate exception: " + e.toString());
        }
        while (!MillerRabbinTest.computeTest(q))
        {
            try{
                q = generateRandom().abs();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Generate exception: " + e.toString());
            }
        }


        BigInteger i = ONE;

        p1 = p.multiply(TWO).multiply(i).add(ONE);
        while (!MillerRabbinTest.computeTest(p1))
        {
            i = i.add(ONE);
            p1 = p.multiply(TWO).multiply(i).add(ONE);
        }

        q1 = q.multiply(TWO).multiply(i).add(ONE);
        while (!MillerRabbinTest.computeTest(q1))
        {
            i = i.add(ONE);
            q1 = q.multiply(TWO).multiply(i).add(ONE);
        }

        big2Primes[0] = p1;
        big2Primes[1] = q1;

        return big2Primes;
    }
}
