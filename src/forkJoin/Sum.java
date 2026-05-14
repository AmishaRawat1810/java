package forkJoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
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

        if (endIndex - startIndex <= 2) {
            for (int i = startIndex; i < endIndex; i++) {
                sum += numbers.get(i);
            }
            return sum;
        }

        int midIndex = (startIndex + endIndex) / 2;
        Sum leftHalf = new Sum(numbers, startIndex, midIndex);
        Sum rightHalf = new Sum(numbers, midIndex, endIndex);

        leftHalf.fork();
        int rJoin = rightHalf.compute();
        int lJoin = leftHalf.join();

        return lJoin + rJoin;
    }

    static void main() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Sum sum1 = new Sum(numbers, 0, numbers.size());

        try (ForkJoinPool pool = new ForkJoinPool()) {
            System.out.println(pool.invoke(sum1));
            pool.shutdown();
        }
    }
}
