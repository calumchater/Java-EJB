package web.controleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import ejb.sessions.* ;
import ejb.entites.* ;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value={})
public class Controleur extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  
  
  public Controleur() {}
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    String url = request.getRequestURL().toString();
    String maVue ="/index.jsp"; // vue par défaut
	

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(maVue);
    dispatcher.forward(request,response);
  }
}
