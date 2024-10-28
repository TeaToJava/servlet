package ru.clevertec.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;


@WebFilter(urlPatterns = {"/paintings/*"})
public class LoggingFilter implements Filter {

    private static Logger logger = LogManager.getLogger(LoggingFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String methodName = httpServletRequest.getMethod();
        logger.info(methodName);
        Enumeration<String> parameters = httpServletRequest.getParameterNames();
        while(parameters.hasMoreElements()){
            String name = parameters.nextElement();
            String value = httpServletRequest.getParameter(name);
            logger.info(name + " " + value);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
