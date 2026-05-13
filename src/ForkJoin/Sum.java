package ForkJoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Sum extends RecursiveTask<Integer> {
    private final List<Integer> numbers;
    private final int startIndex;
    private final int endIndex;

    public Sum(List<Integer> numbers, int startIndex, int endIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        if (startIndex - endIndex <= 2) {
            for (int i = startIndex; i < endIndex; i++) {
                sum += numbers.get(i);
            }
            return sum;
        }

        int midIndex = (startIndex + endIndex) / 2;
        Sum leftHalf = new Sum(numbers, startIndex, endIndex - midIndex);
        Sum rightHalf = new Sum(numbers, midIndex, endIndex - midIndex);

        leftHalf.fork();
        rightHalf.fork();

        int lJoin = leftHalf.join();
        int rJoin = rightHalf.join();

        sum = lJoin + rJoin;
        System.out.println("reached");
        return sum;
    }

    static void main() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Sum sum1 = new Sum(numbers, 0, numbers.size());
        System.out.println(sum1.compute());
    }
}
