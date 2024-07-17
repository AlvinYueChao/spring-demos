//package org.example.alvin.demo.filter;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Component
//public class CustomFilter implements Filter {
//  @Override
//  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//    String requestURI = httpServletRequest.getRequestURI();
//    if (requestURI.startsWith("/api/special")) {
//      RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/");
//
//    } else {
//      filterChain.doFilter(servletRequest, servletResponse);
//    }
//  }
//}
