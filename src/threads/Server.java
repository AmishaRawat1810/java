package threads;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
    private boolean isNewDataAvailable;
    private String data;

    public Server() {
        data = "";
        isNewDataAvailable = false;
    }

    public synchronized void send(String data) {
        System.out.println(Thread.currentThread().getName() + " received data");
        this.data = data;
        isNewDataAvailable = true;
        notifyAll();
    }

    public synchronized String receive() {
        while(!isNewDataAvailable) {
            try {
                System.out.println("waiting for data...");
                wait(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        String result = this.data;
        isNewDataAvailable = false;
        notifyAll();
        return result;
    }

    static void main() {
        Server server = new Server();
        Executor executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 12; i++) {
            final int idx = i;
            executor.execute(() -> server.send("Hello " + idx));
        }

        executor.execute(() -> System.out.println(server.receive()));
    }
}
