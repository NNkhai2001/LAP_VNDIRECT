import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Unit7_2NIO_CopyingAndMovingExample {
    public static void main(String[] args) {
        Path source = Paths.get("C:\\Test1\\hanoijava1.txt");
        Path target = Paths.get("C:\\Test2\\hanoi.txt");
        try {
            //Files.copy(source, target, REPLACE_EXISTING );
            //Files.delete(target);
            Files.createLink(target, source);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}