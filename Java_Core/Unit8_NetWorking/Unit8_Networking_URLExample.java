import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.stream.Stream;

class URLExample {
    public static void main(String[] args) {
        String link = "https://www.google.com.vn/?hl=vi";
        try {
            URL url = new URL(link);
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Port: " + url.getPort());
            System.out.println("Query: " + url.getQuery());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

class URLConnectionExample {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.google.com.vn/?hl=vi");
            try {
                URLConnection connection = url.openConnection();
                InputStream stream = connection.getInputStream();
                int read;
                byte[] bytes = new byte[255];
                while ((read = stream.read(bytes)) != -1) {
                    System.out.println(new String(bytes,0,read));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}