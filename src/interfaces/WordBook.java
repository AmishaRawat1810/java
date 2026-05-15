package interfaces;

import java.util.ArrayList;
import java.util.stream.Stream;

public class WordBook {
    static void main() {
        WordBook book = new WordBook();

        ArrayList<String> stringStream = Stream.of("In this example, widgets is a Collection<Widget>. We create a stream of Widget objects via Collection.stream(), filter it to produce a stream containing only the red widgets, and then transform it into a stream of int values representing the weight of each red widget. Then this stream is summed to produce a total weight.\n".split("\\s+"))
                .limit(5)
                .filter(word -> word.length() > 4)
                .map(word -> word.toLowerCase())
                .collect(() -> new ArrayList<>(),
                        (strings, s) -> strings.add(s.replaceAll("e", "b")),
                        (strings, strings2) -> strings.addAll(strings2)
                );

        System.out.println(stringStream);


    }

    private <T extends Runnable> void run(T t) {
        t.run();
    }
}

