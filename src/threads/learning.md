Core idea of wait / notify
    - Key facts (all must be true):
    - wait() and notify()/notifyAll() are methods on any object (Object methods).
    - You must call them inside a synchronized block/method on the same object 
        (i.e. the thread must own that object’s monitor).


What is Executor / ExecutorService
Executor is just an interface with a single method execute(Runnable command). You never do new Executor() directly.
In practice, you use factory methods from Executors to get an ExecutorService.

    public static void main(String[] args) {
        Server server = new Server();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // one consumer that reads 3 messages
        executor.execute(() -> {
            for (int i = 0; i < 3; i++) {
                String d = server.send();
                System.out.println(Thread.currentThread().getName() + " received: " + d);
            }
        });

        // three producers
        for (int i = 0; i < 3; i++) {
            final int idx = i;
            executor.execute(() -> server.receive("Hello " + idx));
        }

        executor.shutdown();
    }
}