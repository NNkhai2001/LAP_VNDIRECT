import java.io.IOException;
import java.nio.file.*;

public class Unit7_2NIO_WatchFolderExample {
    public static void watch(Path path) {
        FileSystem fs = path.getFileSystem();
        try (WatchService service = fs.newWatchService()) {
            path.register(service, StandardWatchEventKinds.ENTRY_CREATE);
            while (true) {
                WatchKey key = service.take();
                for (WatchEvent watchEvent : key.pollEvents()) {
                    WatchEvent.Kind kind = watchEvent.kind();
                    if (StandardWatchEventKinds.ENTRY_CREATE == kind) {
                        Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        System.out.println("New path created: "+newPath);
                    }

                }
                if (!key.reset()) break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("C:\\Temp");
        watch(path);
    }

}