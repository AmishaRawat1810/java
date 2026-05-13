package CallableAndRunnable;

import java.util.Random;
import java.util.concurrent.*;

public class MyThread {
    static void main() throws Exception {
        //Runnables
        Thread thread = new Thread(new HelloTask());
        thread.start();

        Thread thread2 = new Thread(() -> System.out.println("hello task !"));
        thread2.start();

        //Callables
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            return new Random().nextInt();
        });

        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "Hello";
        };

        Future<String> future2 = executor.submit(task);
        System.out.println(future.get());
        System.out.println("doing something..");
        System.out.println(future2.get());

        executor.shutdown();
    }
}
