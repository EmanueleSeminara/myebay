package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exceptions.CreditoInsufficienteException;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/annuncio/ExecuteBuyAnnuncioServlet")
public class ExecuteBuyAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUtente = request.getParameter("idUtente");
		String idAnnuncio = request.getParameter("idAnnuncio");

		if (!NumberUtils.isCreatable(idUtente) || !NumberUtils.isCreatable(idAnnuncio)) {
			System.out.println("CIRO");
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getAnnuncioServiceInstance().acquista(Long.parseLong(idUtente),
					Long.parseLong(idAnnuncio));
			request.setAttribute("acquisti_list_attribute",
					MyServiceFactory.getAcquistoServiceInstance().listaAcquistiPerIdUtente(Long.parseLong(idUtente)));
		} catch (CreditoInsufficienteException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Il credito residuo non è sufficiente per completare l'acquisto.");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/annuncio/ExecuteListAcquistoServlet").forward(request, response);
	}

}
