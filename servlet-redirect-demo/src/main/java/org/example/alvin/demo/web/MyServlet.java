package org.example.alvin.demo.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class MyServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) {
    String requestURI = req.getRequestURI();
    if (requestURI.equals("/api/special1") || requestURI.equals("/api/special2")) {
      try {
        req.getRequestDispatcher(requestURI.replace("api", "rest")).forward(req, res);
      } catch (ServletException | IOException e) {
        log.error("Caught exception", e);
      }
    } else {
      PrintWriter out = null;
      try {
        out = res.getWriter();
      } catch (IOException e) {
        log.error("Caught exception", e);
      }
      assert out != null;
      out.write("Hello from MyServlet");
    }
  }
}
