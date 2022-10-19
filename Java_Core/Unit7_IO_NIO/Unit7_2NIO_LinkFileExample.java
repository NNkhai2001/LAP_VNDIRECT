import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Unit7_2NIO_LinkFileExample {
    public static void main(String[] args) {
        Path source = Paths.get("C:\\Test1\\hanoijava1.txt");
        Path target = Paths.get("C:\\Test2\\hanoijava2.txt");

        Charset charset = Charset.forName("utf8");
        try (BufferedWriter writer = Files.newBufferedWriter(source,
                charset, StandardOpenOption.APPEND)) {
            writer.write("Java NIO 2 example \r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader = Files.newBufferedReader(target, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}