package edu.java.web;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(value = "/Save", name = "Save-servlet")
public class SaveServlet extends HttpServlet {
    Connection connection = null;
    Statement statement = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:C://Temp//userdb;create=true");
            statement = connection.createStatement();
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getTables(null, null, null, new String[]{"TABLE"});
            if (rs.next() && "hanoi_user" == (rs.getString("TABLE_NAME"))) {return;}
//            statement.executeUpdate("create table hanoi_user(username varchar(500), " +
//                    "password varchar(500), email varchar(1000))");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action").equals("del")) {
            String sql = "delete from hanoi_user where username=\'"+req.getParameter("user")+"\'";
            try {
                statement.executeUpdate(sql);

                req.getRequestDispatcher("view_users.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String sql = "insert into hanoi_user" +"(username,password,email) values"
                +"('"+user+"','"+password+"','"+email+"')";
        try {
            statement.execute(sql);
      //      req.getRequestDispatcher("register.html").forward(req,resp);
            req.getRequestDispatcher("view_users.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace(resp.getWriter());
        }


    }
    @Override
    public void destroy() {
        try {
            connection = DriverManager.getConnection("jdbc:derby:C://Temp//userdb;shutdowm=true");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
