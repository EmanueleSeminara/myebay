package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Acquisto;

@WebServlet("/acquisto/PrepareSearchAcquistoServlet")
public class PrepareSearchAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// questo mi serve per la select di registi in pagina
			request.setAttribute("search_acquisto_attribute", new Acquisto());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/utente/home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/acquisto/search.jsp").forward(request, response);
	}

}
