package streams;

import java.io.*;

public class ExampleBufferIO {
    static void main() throws IOException {
        int b;
        char[] buf = new char[10];

        try (FileReader reader = new FileReader("src/streams/poem.txt")) {
            try (FileWriter writer =
                         new FileWriter("src/streams/sm_copy.txt")) {
                while ((b = reader.read(buf)) != -1) {
                    writer.write(b);
                    System.out.println(buf);
                }
            }
        }

        System.out.println("-".repeat(30));

        BufferedReader inputStream =
                new BufferedReader(new FileReader("src/streams/poem.txt"));
        BufferedWriter outputStream =
                new BufferedWriter(new FileWriter("src/streams/sm_copy.txt"));

        String read;
        while((read = inputStream.readLine()) != null) {
            outputStream.write(read);
            System.out.println(read);
        }
        //just in case
        outputStream.flush();
    }
}
