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

@WebServlet("/annuncio/ExecuteInsertAnnuncioUtenteServlet")
public class ExecuteInsertAnnuncioUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String testoAnnuncioParam = request.getParameter("testoAnnuncio");
		String prezzoParam = request.getParameter("prezzo");
		String[] categorieParam = request.getParameterValues("categoriaInput");
		String idUtenteParam = request.getParameter("idUtente");

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Annuncio annuncioInstance = UtilityForm.createAnnuncioFromParams(testoAnnuncioParam, prezzoParam,
				categorieParam);

		if (!NumberUtils.isCreatable(idUtenteParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		annuncioInstance.setUtenteInserimento(new Utente(Long.parseLong(idUtenteParam)));

		try {
			// se la validazione non risulta ok
			if (!UtilityForm.validateAnnuncioBean(annuncioInstance)) {
				request.setAttribute("insert_annuncio_attr", annuncioInstance);
				// questo mi serve per la select di registi in pagina
				request.setAttribute("categorie_list",
						MyServiceFactory.getCategoriaServiceInstance().listAllElements());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/annuncio/insertUser.jsp").forward(request, response);
				return;
			}

			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
			// occupiamoci delle operazioni di business
			MyServiceFactory.getAnnuncioServiceInstance().inserisciNuovo(annuncioInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/utente/home.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		// uso il sendRedirect con parametro per evitare il problema del double save on
		// refresh
		// response.sendRedirect("ExecuteListAnnuncioServlet?operationResult=SUCCESS");
		response.sendRedirect(request.getContextPath() + "/utente/home.jsp");
	}

}
