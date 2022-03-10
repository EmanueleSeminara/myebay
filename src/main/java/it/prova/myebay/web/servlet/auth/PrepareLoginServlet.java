package it.prova.myebay.web.servlet.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrepareLoginServlet
 */
@WebServlet("/PrepareLoginServlet")
public class PrepareLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("from", request.getParameter("from"));
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
