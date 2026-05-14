package forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MaxOfList extends RecursiveTask<Integer> {
    private final int[] array;
    private final int startIndex;
    private final int endIndex;

    public MaxOfList(int[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Integer compute() {
        if (endIndex - startIndex <= 3) {
            int max = array[startIndex];
            for (int i = startIndex + 1; i < endIndex; i++) {
                max = Math.max(max, array[i]);
            }
            return max;
        }

        int midIndex = (startIndex + endIndex) / 2;
        forkJoin.MaxOfList leftTask = new forkJoin.MaxOfList(array, startIndex, midIndex);
        forkJoin.MaxOfList rightTask = new forkJoin.MaxOfList(array, midIndex, endIndex);

        leftTask.fork();
        Integer rightMax = rightTask.compute();
        Integer leftMax = leftTask.join();

        return Math.max(rightMax, leftMax);
    }

    static void main() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        forkJoin.MaxOfList array1 = new forkJoin.MaxOfList(array, 0, array.length);
        Integer seqMax;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            seqMax = pool.invoke(array1);
        }
        System.out.println("seq max = " + seqMax);
    }
}
