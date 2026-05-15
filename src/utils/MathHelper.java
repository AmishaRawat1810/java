package utils;

public class MathHelper {

    public static boolean isPrime(Integer n) {
        if (n == 2) return true;
        if (n < 2 || n % 2 == 0) return false;
        int limit = (int) Math.sqrt(n);
        for (int i = 3; i <= limit; i+=2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }
}
