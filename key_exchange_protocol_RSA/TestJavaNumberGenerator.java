/*package com.company;*/

import junit.framework.Assert;
import org.junit.Test;
import java.math.BigInteger;

public class TestJavaNumberGenerator {

    @Test
    public void testGeneratePrimesNumber16() throws Exception {
        JavaNumberGenerator bi = new JavaNumberGenerator(16);
        BigInteger pq[] = bi.generate2PrimesNumber();
        for(BigInteger b : pq)
        {
            boolean result = MillerRabbinTest.computeTest(b);
            Assert.assertEquals("Generated 'prime' BI is not prime", true, result);
        }
    }

    @Test
    public void testGeneratePrimesNumber64() throws Exception {
        JavaNumberGenerator bi = new JavaNumberGenerator(64);
        BigInteger pq[] = bi.generate2PrimesNumber();
        for(BigInteger b : pq)
        {
            boolean result = MillerRabbinTest.computeTest(b);
            Assert.assertEquals("Generated 'prime' BI is not prime", true, result);
        }
    }

    @Test
    public void testGeneratePrimesNumber128() throws Exception {
        JavaNumberGenerator bi = new JavaNumberGenerator(128);
        BigInteger pq[] =  bi.generate2PrimesNumber();
        for(BigInteger b : pq)
        {
            boolean result = MillerRabbinTest.computeTest(b);
            Assert.assertEquals("Generated 'prime' BI is not prime", true, result);
        }
    }
}
