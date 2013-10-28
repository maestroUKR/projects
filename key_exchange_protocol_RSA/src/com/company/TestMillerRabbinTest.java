package com.company;

import junit.framework.Assert;
import java.math.BigInteger;
import org.junit.Test;


public class TestMillerRabbinTest {

    /**
     * tests for prime detection
     *
     * @throws Exception
     */
    @Test
    public void testComputeTest_97() throws Exception {
        boolean result = MillerRabbinTest.computeTest(new BigInteger("97", 10));
        Assert.assertEquals("97 must be a prime", true, result);
    }

    @Test
    public void testComputeTest_93() throws Exception {

        boolean result = MillerRabbinTest.computeTest(new BigInteger("93", 10));
        Assert.assertEquals("93 must NOT be a prime", false, result);
    }

    @Test
    public void testComputeTest_37019() throws Exception {

        boolean result = MillerRabbinTest.computeTest(new BigInteger("37019", 10));
        Assert.assertEquals("37019 must NOT be a prime", true, result);
    }

    @Test
    public void testComputeTest_999() throws Exception {

        boolean result = MillerRabbinTest.computeTest(new BigInteger("999", 10));
        Assert.assertEquals("999 must NOT be a prime", false, result);
    }

    @Test
    public void testComputeTest_777() throws Exception {

        boolean result = MillerRabbinTest.computeTest(new BigInteger("777", 10));
        Assert.assertEquals("77 must NOT be a prime", false, result);
    }

    @Test
    public void testComputeTest_443() throws Exception {

        boolean result = MillerRabbinTest.computeTest(new BigInteger("443", 10));
        Assert.assertEquals("443 must be a prime", true, result);
    }

    @Test
    public void testComputeTest_997() throws Exception {

        boolean result = MillerRabbinTest.computeTest(new BigInteger("997", 10));
        Assert.assertEquals("997 must be a prime", true, result);
    }

    @Test
    public void testComputeTest_631() throws Exception {

        boolean result = MillerRabbinTest.computeTest(new BigInteger("631", 10));
        Assert.assertEquals("631 must be a prime", true, result);
    }

    // --------------------------------------------------------------------------------------------------------

    /**
     * tests for GCD
     *
     * @throws Exception
     */
    @Test
    public void testGcd_2_11() throws Exception {
        BigInteger gcd = MillerRabbinTest.gcd(new BigInteger(String.valueOf(2), 10), new BigInteger(String.valueOf(11), 10));
        Assert.assertEquals("GCD must be = 1", BigInteger.ONE, gcd);
    }

    @Test
    public void testGcd_4_68() throws Exception {
        BigInteger gcd = MillerRabbinTest.gcd(new BigInteger(String.valueOf(4), 10), new BigInteger(String.valueOf(68), 10));
        Assert.assertEquals("GCD must be = 4", new BigInteger(String.valueOf(4), 10), gcd);
    }

    // ------------------------------------------------------------------------------------------------------

}
