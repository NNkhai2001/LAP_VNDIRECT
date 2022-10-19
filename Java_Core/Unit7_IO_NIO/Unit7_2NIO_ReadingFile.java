import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public  class Unit7_2NIO_ReadingFile {
    public static void main(String[] args) {
        Path  path = Paths.get("C:\\Temp");
        Path path2  = path.resolve("hanoijava.txt");
        Charset charset = Charset.forName("utf8");
        try (BufferedReader reader = Files.newBufferedReader(path2, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}