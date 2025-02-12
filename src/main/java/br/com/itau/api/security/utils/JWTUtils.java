package br.com.itau.api.security.utils;

import java.util.regex.Pattern;

public abstract class JWTUtils {
	
    public static boolean containsNumbers(String str) {
        var p = Pattern.compile(".*\\d.*");
        return p.matcher(str).matches();
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        
        if (number == 2) {
            return true;
        }
       
        if (number % 2 == 0) {
            return false;
        }

        var limit = (int) Math.sqrt(number);

        for (var i = 3; i <= limit; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

}
