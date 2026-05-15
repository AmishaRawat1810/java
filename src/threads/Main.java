package threads;

public class Main {

    static void main() throws InterruptedException {
        Thread thread = new Thread(Main::threadMsg);
        thread.start();
        thread.join();
        System.out.println("execution of thread completed....");

        Counter counter = new Counter(0);
        Thread thread1 = new Thread(() -> counter.increment(10));
        Thread thread2 = new Thread(() -> counter.increment(10));

//      A race condition in Java occurs when two or more threads access and modify shared data simultaneously,
//      leading to unpredictable or incorrect results because the outcome depends on the timing of the threads

        thread1.start();
        thread2.start();

        thread1.join();

        System.out.println(counter.getCount());
        System.out.println("execution is completed of 2 threads");
    }

    private static void threadMsg(){
        System.out.println("thread: " + Thread.currentThread() + " running");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        Thread[#27,Thread-0,5,main]
//        Breakdown:
//        Thread – It is a java.lang.Thread instance.
//        #27 – An internal thread ID assigned by the JVM (not the OS thread ID).
//        Thread-0 – The thread’s name (default name given by JVM when you don’t set one).
//        5 – The thread’s priority (default is usually 5, in the range 1–10).
//        main – The thread group this thread belongs to (here, the default main group).
    }
}
