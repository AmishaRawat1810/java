package threads;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    static private Set<Number> syncHashSet = new HashSet<>();
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock writeLock = lock.writeLock();
    static Lock readLock = lock.readLock();

    static void main() {

        Thread reader1 = new Thread(() -> contains(1));
        reader1.start();

        Thread reader2 = new Thread(() -> contains(2));
        reader2.start();

        Thread writer = new Thread(() -> put(1));
        writer.start();
        System.out.println(syncHashSet.stream().toList());
    }

    private static void contains(int value) {
        try{
            readLock.lock();
            threadMsg("Reader thread is locked");
            System.out.println(syncHashSet.contains(value));
        } finally {
            readLock.unlock();
            threadMsg("Reader thread is unlocked");
        }
    }

    private static void put(Number value) {
        try {
            writeLock.lock();
            threadMsg("writer thread is locked");
            syncHashSet.add(value);
        } finally {
            writeLock.unlock();
            threadMsg("writer thread is unlocked");
        }
    }

    private static void threadMsg(String msg) {
        System.out.println(msg);
    }

}
