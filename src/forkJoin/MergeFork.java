package forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeFork extends RecursiveAction {
    private final int[] array;
    private final int startIndex;
    private final int endIndex;

    public MergeFork(int[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected void compute() {
        if (startIndex < endIndex ) {
            int midIndex = (endIndex + startIndex) / 2;
            MergeFork leftTask = new MergeFork(array, startIndex, midIndex);
            MergeFork rightTask = new MergeFork(array, midIndex + 1, endIndex);

            invokeAll(leftTask,rightTask);

//            invokeAll already waits for both tasks
//            doing join() explicitly makes it very clear that you want them finished before merging

//            leftTask.join();
//            rightTask.join();

            merge(array, startIndex, midIndex, endIndex);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int leftLength = mid - left + 1;
        int rightLength = right - mid;

        int[] leftHalf = new int[leftLength];
        int[] rightHalf = new int[rightLength];

        // arraycopy(Object src, int srcPos, Object dest, int destPos, int length)

        System.arraycopy(array, left, leftHalf, 0, leftLength);
        System.arraycopy(array, mid + 1, rightHalf, 0, rightLength);

        int l = 0, r = 0;
        int k = left;

        while (l < leftLength && r < rightLength ) {
            if (leftHalf[l] <= rightHalf[r]) {
                array[k] = leftHalf[l++];
            } else {
                array[k] = rightHalf[r++];
            }
            k++;
        }

        while(l < leftLength) {
            array[k++] = leftHalf[l++];
        }

        while(r < rightLength) {
            array[k++] = rightHalf[r++];
        }
    }

    static void main() {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        MergeFork sort = new MergeFork(array, 0, array.length - 1);
        try (ForkJoinPool pool = new ForkJoinPool()) {
            pool.invoke(sort);
        }

        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
