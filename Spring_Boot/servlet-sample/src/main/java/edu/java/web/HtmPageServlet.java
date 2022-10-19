package edu.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ServletSecurity(
        @HttpConstraint(
                transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL,
                rolesAllowed = {"read"}
        )
)
@WebServlet(value = "/html", name = "html-html")
public class HtmPageServlet extends HelloServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Welcome to my computer!</title></head>");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

