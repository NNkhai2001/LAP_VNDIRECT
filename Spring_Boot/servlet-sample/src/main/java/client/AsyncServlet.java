package client;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(urlPatterns = "/sync", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext ctx = req.startAsync();
        ctx.setTimeout(60 * 1000);
        ctx.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Writer writer = ctx.getResponse().getWriter();
                    URL obj = new URL("https://www.youtube.com/");
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    writer.write("Hello Async");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    ctx.complete();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
