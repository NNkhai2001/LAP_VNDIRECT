package client;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class SimpleServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\n\n Servlet destroy");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\n\n started");
    }
}
