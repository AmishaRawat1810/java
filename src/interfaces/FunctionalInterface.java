package interfaces;

import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterface {
    static void main() {
        Function<String, Boolean> isLongName = (s) -> s.length() > 5;
        Function<String, String> toLowercase = (s) -> s.toLowerCase();
        Function<String, Boolean> startsWithS = (s) -> s.startsWith("s");
        Function<Integer, Integer> multiplyBy2 = x -> x * 2;
        Function<Integer, Integer> add3 = x -> x + 3;
        Function<Integer, Integer> multiplyAndAdd = multiplyBy2.compose(add3);

        System.out.println(isLongName.apply("sam"));
        System.out.println(toLowercase.andThen(startsWithS).apply("sammy"));
        System.out.println(toLowercase.andThen(isLongName).apply("sammy"));
        System.out.println(multiplyAndAdd.apply(3));

        Predicate<String> longName = (s) -> s.length() > 5;
        Predicate<String> startsWithA = (s) -> s.toLowerCase().startsWith("a");
        Predicate<String> combined = longName.and(startsWithA);

        System.out.println(combined.test("sammmy"));
        System.out.println(longName.or(startsWithA).test("sammmy"));
        System.out.println(longName.negate().test("sammmy"));
    }
}



