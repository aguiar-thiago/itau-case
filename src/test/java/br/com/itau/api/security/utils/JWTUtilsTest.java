package br.com.itau.api.security.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JWTUtilsTest {

    @Test
    void testContainsNumbers_WithNumbers() {
        String str = "abc123";
        assertTrue(JWTUtils.containsNumbers(str), "A string deve conter números.");
    }

    @Test
    void testContainsNumbers_WithoutNumbers() {
        String str = "abc";
        assertFalse(JWTUtils.containsNumbers(str), "A string não deve conter números.");
    }

    @Test
    void testIsPrime_WithPrimeNumber() {
        int number = 5;
        assertTrue(JWTUtils.isPrime(number), "5 deve ser um número primo.");
    }

    @Test
    void testIsPrime_WithNonPrimeNumber() {
        int number = 4;
        assertFalse(JWTUtils.isPrime(number), "4 não deve ser um número primo.");
    }

    @Test
    void testIsPrime_WithNegativeNumber() {
        int number = -3;
        assertFalse(JWTUtils.isPrime(number), "-3 não é um número primo.");
    }

    @Test
    void testIsPrime_WithZero() {
        int number = 0;
        assertFalse(JWTUtils.isPrime(number), "0 não é um número primo.");
    }

    @Test
    void testIsPrime_WithOne() {
        int number = 1;
        assertFalse(JWTUtils.isPrime(number), "1 não é um número primo.");
    }
}
