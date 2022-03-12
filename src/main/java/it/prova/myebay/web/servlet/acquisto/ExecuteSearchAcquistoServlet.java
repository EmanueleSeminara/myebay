package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/acquisto/ExecuteSearchAcquistoServlet")
public class ExecuteSearchAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descrizioneParam = request.getParameter("descrizione");
		String prezzoParam = request.getParameter("prezzo");
		String dataParam = request.getParameter("data");

		String idUtente = request.getParameter("idUtente");

		Acquisto example = UtilityForm.createAcquistoFromParams(descrizioneParam, prezzoParam, dataParam);

		if (!NumberUtils.isCreatable(idUtente)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}

		try {
			example.setUtenteAcquirente(new Utente(Long.parseLong(idUtente)));
			System.out.println(MyServiceFactory.getAcquistoServiceInstance().findByExample(example).size());
			request.setAttribute("acquisti_list_attribute",
					MyServiceFactory.getAcquistoServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/utente/home.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/acquisto/list.jsp").forward(request, response);
	}

}
