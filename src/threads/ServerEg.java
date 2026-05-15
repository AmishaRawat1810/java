package threads;

public class ServerEg {
    private String data;
    private boolean transfer = true;
//    If this variable is true, the Receiver should wait for Sender to send the message.
//    If it’s false, Sender should wait for Receiver to receive the message.

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Receiver got interrupted");
            }
        }
        transfer = true; //because it is consumed so sender can send new data
        String result = data;
        notifyAll();
        return result;
    }

    public synchronized void send(String data) {
        while(!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Receiver got interrupted");
            }
        }
        transfer = false; //because it is not consumed by the receiver
        this.data = data;
        notifyAll();
    }

    static void main() {
        ServerEg serverEg1 = new ServerEg();

    }
}
