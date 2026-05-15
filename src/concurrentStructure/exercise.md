A good basic exercise is to build a tiny in‑memory “log processing system” using a `BlockingQueue` plus a `ConcurrentHashMap`.

### Exercise description

Implement a program with:

- One or more **producer** threads that generate “log events” and put them into a `BlockingQueue`.
- One **consumer** thread that takes events from the `BlockingQueue`, processes them, and updates a `ConcurrentHashMap<String, Integer>` that counts occurrences per log level (e.g. INFO, WARN, ERROR). [geeksforgeeks](https://www.geeksforgeeks.org/java/blockingqueue-interface-in-java/)

### Requirements

- Use `BlockingQueue<LogEvent>` (e.g. `LinkedBlockingQueue`) for communication between producers and consumer. [docs.oracle](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html)
- Use `ConcurrentHashMap<String, Integer>` for counts, and update it using `compute`, `merge`, or `computeIfPresent` for thread‑safe increments. [digitalocean](https://www.digitalocean.com/community/tutorials/concurrenthashmap-in-java)
- Run:
    - 2 producer threads that generate random events with level chosen from `{INFO, WARN, ERROR}` and message like `"event-<id>"`, sleeping a bit between events.
    - 1 consumer thread that:
        - Calls `queue.take()` in a loop.
        - On each event, increments the count for that level in the map.
- After some time (e.g. 5 seconds), stop producers, send a special “poison pill” event into the queue to stop the consumer, then print the final counts map.

### Hints for implementation

- Define a simple immutable `LogEvent` class with `level` and `message`.
- Initialize the map with zero counts for all levels, or use `map.merge(level, 1, Integer::sum)` to create entries on the fly. [digitalocean](https://www.digitalocean.com/community/tutorials/concurrenthashmap-in-java)
- Use `ExecutorService` to manage producer/consumer threads cleanly, and `awaitTermination` to wait for them to finish. [digitalocean](https://www.digitalocean.com/community/tutorials/java-blockingqueue-example)
- To practice more, extend it so:
    - Each level has its own `BlockingQueue<LogEvent>`, and you store them in a `ConcurrentHashMap<String, BlockingQueue<LogEvent>>`. [stackoverflow](https://stackoverflow.com/questions/20945984/is-there-blockingmap-as-blockingqueue-in-java)
    - Different consumer threads handle different levels.

```java
class LogProcessorTest {
    @Test
    void countsEventsByLogLevel() throws Exception {
        // given: a log processor that uses BlockingQueue + ConcurrentHashMap
        LogProcessor processor = new LogProcessor(); // your class
        processor.start(); // starts producers + consumer threads

        // when: we let it run for a short while
        Thread.sleep(3000);

        // then: stop and inspect counts
        processor.stop(); // joins threads, shuts down executors, etc.
        Map<String, Integer> counts = processor.getLevelCounts();

        assertNotNull(counts);
        // At least some events should have been processed
        assertTrue(counts.values().stream().mapToInt(Integer::intValue).sum() > 0);

        // Levels should be present and non‑negative
        assertTrue(counts.getOrDefault("INFO", 0) >= 0);
        assertTrue(counts.getOrDefault("WARN", 0) >= 0);
        assertTrue(counts.getOrDefault("ERROR", 0) >= 0);
    }
}
```
