import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

public class Unit7_2NIO_WritingFileExample {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Temp\\hanoijava.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("utf8"), CREATE, APPEND)) {
            for (int i = 0; i < 10; i++) {
                writer.write("Nguyen van " + i + "\r\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}