package streams;

import java.io.*;

public class ExampleFileIO {
    static FileInputStream inputStream;
    static FileOutputStream outputStream;

    static void main() throws IOException {
        {
            try {
                inputStream = new FileInputStream("src/Streams/sm.txt");
                outputStream = new FileOutputStream("src/Streams/sm_copy" +
                    ".txt");
                int b;
                while((b = inputStream.read()) != -1) {
                    outputStream.write(b);
                    System.out.println(b);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
