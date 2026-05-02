import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Algorithm;
import utils.MathHelper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTest {

    List<Integer> nums;
    List<String> words;

    @BeforeEach
    void setUp() {
        nums = Arrays.asList(1,2,3,4,5,6,7,8);
        words = Arrays.asList("helo","hi","asa","olo");
    }

    @Test
    void countMatching() {
        int count = Algorithm.countMatching(nums, MathHelper::isPrime);
        assertEquals(4, count);
        int count2 = Algorithm.countMatching(words, MathHelper::isPalindrome);
        assertEquals(2, count2);
    }

    @Test
    void swapElementPos() {
        Algorithm.swapElementPos(words, 1, 2);
        assertEquals("asa", words.get(1));
        assertEquals("hi", words.get(2));
    }
}