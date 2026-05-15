package concurrentStructure;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class LogEvent {
    private final BlockingQueue<String> queue;
    private final ConcurrentHashMap<String, Integer> logCount;

    private LogEvent() {
        this.queue = new LinkedBlockingDeque<String>(10);
        logCount = new ConcurrentHashMap<>(10);
    }

    public void log(String level, String msg) {
        queue.offer(level);
    }
}
