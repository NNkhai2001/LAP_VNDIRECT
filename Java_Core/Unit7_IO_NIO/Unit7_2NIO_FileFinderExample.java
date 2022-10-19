import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;

public class Unit7_2NIO_FileFinderExample {
      static class Finder extends SimpleFileVisitor<Path>{
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{java,class}");

        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            if(matcher.matches(path.getFileName())) {
                System.out.println("found: "+path);

            }
            return FileVisitResult.CONTINUE;
        }

    }

    public static void main(String[] args) {
        Path path = Paths.get("C:\\Temp");
        Finder finder = new Finder();
        try {
            Files.walkFileTree(path,finder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}