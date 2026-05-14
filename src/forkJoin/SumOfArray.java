package forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumOfArray extends RecursiveTask<Integer> {

    private final int[] array;
    private final int startIndex;
    private final int endIndex;

    public SumOfArray(int[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Integer compute() {
        if (endIndex - startIndex <= 2) {
            int sum = 0;
            for (int i = startIndex; i < endIndex; i++) {
                sum += array[i];
            }
            return sum;
        }

        int midIndex = (startIndex + endIndex) / 2;
        SumOfArray leftTask = new SumOfArray(array, startIndex, midIndex);
        SumOfArray rightTask = new SumOfArray(array, midIndex, endIndex);

        leftTask.fork();
        Integer rightSum = rightTask.compute();
        Integer leftSum = leftTask.join();

        return rightSum + leftSum;
    }

    static void main() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        SumOfArray array1 = new SumOfArray(array, 0, array.length);
        try (ForkJoinPool pool = new ForkJoinPool()) {
            System.out.println(pool.invoke(array1));
        }
    }
}
