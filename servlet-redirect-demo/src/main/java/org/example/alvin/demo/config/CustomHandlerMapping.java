package org.example.alvin.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomHandlerMapping extends AbstractHandlerMapping {
  @Override
  protected Object getHandlerInternal(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    if (shouldSkipServlet(requestURI)) {
      return new CustomHandler();
    }

    return null;
  }

  private boolean shouldSkipServlet(String requestURI) {
    return "/api/special1".equals(requestURI) || "/api/special2".equals(requestURI);
  }

  private static class CustomHandler implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
      return handler instanceof CustomHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      response.sendRedirect(request.getRequestURI());
      return null;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
      return -1;
    }
  }
}
