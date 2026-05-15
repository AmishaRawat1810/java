package streams;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Password {
    static void main() {
        Console console = System.console();

        if (console == null) {
            System.err.println("No console created.");
            System.exit(1);
        }

        String login = console.readLine("Enter login username: ");
        char[] password = console.readPassword("Enter password: ");

        try {
            if (verify(login, password)) {
                System.out.println("Logged in...");
            } else {
                System.out.println("Try again...\nDetails:\n" +
                        login + new String(password));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean verify(String username, char[] password) throws IOException {
        Path path = getFilePath();

        try (Scanner reader =
                     new Scanner(new BufferedReader(new FileReader(path.toString()))).useDelimiter("\n")) {

            while (reader.hasNext()) {
                String next = reader.next();
                if (next.matches(String.format("^%s,%s", username,
                        new String(password))
                )) {
                    return true;
                }
            }

            return false;
        }
    }

    private static Path getFilePath() throws IOException {
        Path path = Paths.get("streams", "details.csv");

        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        return path;
    }
}
