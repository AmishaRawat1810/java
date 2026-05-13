package CallableAndRunnable;

public class HelloTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello task !");
    }
}
