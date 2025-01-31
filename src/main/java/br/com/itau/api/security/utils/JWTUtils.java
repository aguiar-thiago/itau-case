package br.com.itau.api.security.utils;

import java.util.regex.Pattern;

public abstract class JWTUtils {
	
    public static boolean containsNumbers(String str) {
        Pattern p = Pattern.compile(".*\\d.*");
        return p.matcher(str).matches();
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
