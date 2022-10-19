import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Filter;

class PathExample {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Temp");
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return entry.toFile().isDirectory();
            }
        };
        System.out.println("This is "+(Files.isDirectory(path)? "file":"folder")+"!");
        System.out.println(Files.exists(path));
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path,filter)) {
            for (Path p : directoryStream) {
                System.out.println(p.getFileName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}