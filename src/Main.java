import utils.Algorithm;
import utils.MathHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    static void main() {
//        Write a generic method to count the number of elements in a collection that have a specific property (for example, odd integers, prime numbers, palindromes).
        Collection<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8);
        Collection<String> words = Arrays.asList("helo","hi","asa","olo");
        int primeCount = Algorithm.countMatching(nums, MathHelper::isPrime);
        int palindromeCount = Algorithm.countMatching(words, MathHelper::isPalindrome);
        System.out.println(primeCount);
        System.out.println(palindromeCount);

//        Write a generic method to exchange the positions of two different elements in an array
        ArrayList<Integer> numbers = new ArrayList<>(nums);
        Algorithm.swapElementPos(numbers, 1, 2);
        System.out.println(numbers.get(1) + " " + numbers.get(2));

//        Write a generic method to find the maximal element in the range [begin, end) of a list.


    }
}