package br.com.itau.api.security.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JWTUtilsTest {

    @Test
    void testContainsNumbers_WithNumbers() {
    	var str = "abc123";
        assertTrue(JWTUtils.containsNumbers(str), "A string deve conter números.");
    }

    @Test
    void testContainsNumbers_WithoutNumbers() {
    	var str = "abc";
        assertFalse(JWTUtils.containsNumbers(str), "A string não deve conter números.");
    }

    @Test
    void testIsPrime_WithPrimeNumber() {
    	var number = 5;
        assertTrue(JWTUtils.isPrime(number), "5 deve ser um número primo.");
    }

    @Test
    void testIsPrime_WithPrimeNumber2() {
    	var number = 7841;
        assertTrue(JWTUtils.isPrime(number), "7841 deve ser um número primo.");
    }

    @Test
    void testIsPrime_WithNegativeNumber() {
    	var number = -3;
        assertFalse(JWTUtils.isPrime(number), "-3 não é um número primo.");
    }

    @Test
    void testIsPrime_WithZero() {
    	var number = 7842;
        assertFalse(JWTUtils.isPrime(number), "7842 não é um número primo.");
    }

    @Test
    void testIsPrime_primeEvenNumber() {
    	var number = 2;
        assertTrue(JWTUtils.isPrime(number), "2 o único número par primo.");
    }
}
