package utils;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Algorithm {
    public static <T> int countMatching(Collection<T> coll, Predicate<T> property) {
        int count = 0;
        for (T item: coll) {
            if(property.test(item)) ++count;
        }
        return count;
    }

    public static <T> void swapElementPos(List<T> list, int pos1, int pos2) {
        T temp = list.get(pos1);
        list.set(pos1, list.get(pos2));
        list.set(pos2, temp);
    }

}
