package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListAcquistoServlet
 */
@WebServlet("/annuncio/ExecuteListAcquistoServlet")
public class ExecuteListAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteListAcquistoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUtente = request.getParameter("idUtente");
		System.out.println(idUtente);

		if (!NumberUtils.isCreatable(idUtente)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore, id non valido..........");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		try {
			request.setAttribute("acquisti_list_attribute",
					MyServiceFactory.getAcquistoServiceInstance().listaAcquistiPerIdUtente(Long.parseLong(idUtente)));
			request.getRequestDispatcher("/acquisto/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
	}

}
