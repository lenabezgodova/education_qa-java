package ru.qa.test.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

    @Test
    public void testPrime_1(){
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));

    }

    @Test(enabled = false)
    public void testPrime_2(){
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimeWhile(){
        Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimeLong(){
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

}
