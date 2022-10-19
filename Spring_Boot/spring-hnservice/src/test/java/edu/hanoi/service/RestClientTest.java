package edu.hanoi.service;

import javax.xml.bind.DatatypeConverter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClientTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/list/users");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept","Application/Json");
        String account = "test3:depzai";
        String basicAuth = "Basic " +javax.xml.bind.DatatypeConverter.printBase64Binary(account.getBytes());
        connection.setRequestProperty("Authorization",basicAuth);
        InputStream stream = connection.getInputStream();
        byte[] bytes = new byte[4*1024];
        int read = -1;
        try{
            while ((read = stream.read(bytes)) != -1) {
                System.out.println(new String(bytes,0,read));
            }
        }finally {
            stream.close();
        }
    }
}
