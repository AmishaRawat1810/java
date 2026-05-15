package threads;

public class Counter {
    private int count;

    public Counter(int count) {
        this.count = count;
    }

    public void increment(int times) {
        System.out.println("before: " + "thread: " + Thread.currentThread().getName() + " running");
        for (int i = 0; i < times; i++) {
//            synchronized (this) {
                this.count++;
//            }
        System.out.println("after thread : " + Thread.currentThread().getName() + " count: " + count);
        }
    }

    public int getCount() {
        return count;
    }
}
