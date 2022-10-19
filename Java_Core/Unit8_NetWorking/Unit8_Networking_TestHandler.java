import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

class TestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String message = "<html><h1>This is the response Ngoc Khai</h1></html>";
        httpExchange.sendResponseHeaders(10, message.length());
        try (OutputStream out = httpExchange.getResponseBody()) {
            out.write(message.getBytes());
        }
    }
}

class HttpServerExample {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/test",new TestHandler());
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.start();

    }
}