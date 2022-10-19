import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

class HttpHeaderExample {
    public static void main(String[] args) throws IOException {

        URL url = new URL("https://www.google.com.vn/?hl=vi");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        System.out.println("Http method: " + connection.getRequestMethod());

        Map<String, List<String>> headers = connection.getHeaderFields();
        BiConsumer<String, List<String>> header = (key, values) -> {
            System.out.println("key: " + key + " Values: " + values);
        };
        headers.forEach(header);
    }
}

class HttpConnectionExample {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.google.com.vn/?hl=vi");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Path path = Paths.get("C:\\Temp\\test.html");
        try (InputStream inputStream = connection.getInputStream();
             OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        ) {
            int read;
            byte[] bytes = new byte[255];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes,0,read);
            }
        } finally {
            Desktop.getDesktop().open(path.toFile());
        }
    }
}