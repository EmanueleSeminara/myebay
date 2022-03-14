package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/annuncio/ExecuteDeleteAnnuncioUtenteServlet")
public class ExecuteDeleteAnnuncioUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idAnnuncioParam = request.getParameter("idAnnuncio");
		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idAnnuncioParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}

		try {
			// novità rispetto al passato: abbiamo un overload di rimuovi che agisce per id
			// in questo modo spostiamo la logica di caricamento/rimozione nel service
			// usando la stessa finestra di transazione e non aprendo e chiudendo due volte
			// inoltre mi torna utile quando devo fare rimozioni eager
			MyServiceFactory.getAnnuncioServiceInstance().rimuovi(Long.parseLong(idAnnuncioParam));
		} catch (ElementNotFoundException e) {
			request.setAttribute("idUtente", idUtenteParam);
			request.getRequestDispatcher("ExecuteListAnnuncioUtenteServlet?operationResult=NOT_FOUND").forward(request,
					response);
			return;
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		// request.setAttribute("idUtente", idUtenteParam);
		// String redirect =
		// "ExecuteListAnnuncioUtenteServlet?operationResult=SUCCESS&idUtente=" +
		// idUtenteParam;
		// request.getRequestDispatcher("../utente/home.jsp").forward(request,
		// response);
		response.sendRedirect(request.getContextPath() + "/utente/home.jsp");
	}

}
