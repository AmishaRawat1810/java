package streams;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class ScanXcan {
    static void main() {

        try {
            Path path = Path.of("src/streams/sm.txt");
            Scanner scanner =
                    new Scanner(new BufferedReader(new FileReader(path.toString()))).useDelimiter(",");
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new FileWriter("src/streams/sm_copy.txt"));

            while (scanner.hasNext()) {
                String next = scanner.next();
                bufferedWriter.write(next);
                System.out.println(next);
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
