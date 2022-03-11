package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/annuncio/ExecuteSearchAnnuncioUtenteServlet")
public class ExecuteSearchAnnuncioUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String testoParam = request.getParameter("testo");
		String prezzoParam = request.getParameter("prezzo");
		String[] categorieParam = request.getParameterValues("categoriaInput");
		String idUtente = request.getParameter("idUtente");

		Annuncio example = UtilityForm.createAnnuncioFromParams(testoParam, prezzoParam, categorieParam);

		if (!NumberUtils.isCreatable(idUtente)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		

		try {
			example.setUtenteInserimento(new Utente(Long.parseLong(idUtente)));
			request.setAttribute("annunci_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().findByExampleForUser(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/annuncio/listUser.jsp").forward(request, response);
	}

}
