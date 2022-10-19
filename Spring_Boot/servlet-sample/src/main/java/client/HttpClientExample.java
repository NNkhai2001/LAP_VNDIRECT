package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientExample {
    public static void main(String[] args) throws IOException, InterruptedException {
//        BufferedReader reader = null;
//        HttpURLConnection con = null;
//        try {
//            URL url = new URL("http://localhost:8080/test/table1");
//            con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.addRequestProperty("Accept-Encoding", "gzip");
//
//            int responseCode = con.getResponseCode();
//            System.out.println("Response code: " + responseCode);
//
//            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (ProtocolException e) {
//            throw new RuntimeException(e);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (reader != null) {
//                reader.close();
//            }
//        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                URL obj = null;
                try {
                    obj = new URL("http://localhost:8080/test/sync");
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.addRequestProperty("Accept-Encoding", "gzip");

                    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        executor.shutdown();
        Thread.sleep(5000);
    }
}
