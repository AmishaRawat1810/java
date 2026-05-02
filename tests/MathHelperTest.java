import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.MathHelper;

import static org.junit.jupiter.api.Assertions.*;

class MathHelperTest {

    @Test
    void isPrime() {
        Assertions.assertTrue(MathHelper.isPrime(2));
        Assertions.assertTrue(MathHelper.isPrime(7));
        assertFalse(MathHelper.isPrime(9));
        assertFalse(MathHelper.isPrime(1));
    }

    @Test
    void isPalindrome() {
        assertTrue(MathHelper.isPalindrome("heleh"));
        assertFalse(MathHelper.isPalindrome("heleha"));
        assertFalse(MathHelper.isPalindrome(null));
    }
}
