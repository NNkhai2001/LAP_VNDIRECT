package edu.java.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HitCounter extends HttpServlet {
    private int hitCount;
    public void init(){
        hitCount = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (hitCount = 0; hitCount < 10; hitCount++) {

        hitCount++;
        }
        resp.setContentType("text/html");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<br>Hit count = "+hitCount);
        resp.getWriter().println("</html>");
    }
}
