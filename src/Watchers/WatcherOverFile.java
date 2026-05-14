package Watchers;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatcherOverFile {
    static void main(String[] args) {

        Path filePath = Paths.get(args[0]).toAbsolutePath();
        Path parentDir = filePath.getParent();
        Path fileName = filePath.getFileName();

        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            parentDir.register(watcher,ENTRY_CREATE,ENTRY_DELETE, ENTRY_MODIFY);

            for (;;) {
                WatchKey key = watcher.take();

                for (WatchEvent<?> pollEvent : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = pollEvent.kind();
                    Path changed = (Path) pollEvent.context();

                    if (!changed.equals(fileName)) {
                        continue;
                    }

                    System.out.format("Event %s on %s%n", kind.name(), fileName);
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
