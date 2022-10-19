package edu.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(value = "/table1", name = "html-page1")
public class tableServlet extends HtmPageServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head><title> Welcome to our website!</title></head>");
        writer.print("<body> <h1 style=color: red> Student </h1>");
        writer.println("<table border=\"1\">\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td>#</td>\n" +
                "\t\t\t<td>Name</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td>1</td>\n" +
                "\t\t\t<td>Nguyen Van A</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td>2</td>\n" +
                "\t\t\t<td>Tran Thi B</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td>3</td>\n" +
                "\t\t\t<td>Le Van C</td>\n" +
                "\t\t</tr>\n" +
                "\t</table>");
        writer.println("</html>");
    }
}
