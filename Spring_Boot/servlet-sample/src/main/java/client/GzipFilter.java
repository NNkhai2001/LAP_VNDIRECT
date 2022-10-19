package client;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/table1"})
public class GzipFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("\n Destroy filter!\n");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        PrintWriter writer = servletResponse.getWriter();
//        writer.println("=====\n Filter intercepted \n=====");
//        writer.flush();

        //  filterChain.doFilter(servletRequest,servletResponse);
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String encoding = httpServletRequest.getHeader("Accept-Encoding");
        if(encoding != null && encoding.contains("gzip")) {
            httpServletResponse.addHeader("Content-Encoding","gzip");
            GzipServletResponseWrapper gZipServletResponseWrapper = new GzipServletResponseWrapper(httpServletResponse);
            filterChain.doFilter(servletRequest,gZipServletResponseWrapper);
            gZipServletResponseWrapper.close();
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("\n Destroy filter\n");
    }
}
