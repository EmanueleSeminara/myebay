package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/ExecuteEditUtenteServlet")
public class ExecuteEditUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUtente = request.getParameter("idUtente");

		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String statoParam = request.getParameter("stato");
		String[] ruoliParam = request.getParameterValues("ruoloInput");

		Utente utenteInstance = UtilityForm.createUtenteFromParamsForEdit(nomeParam, cognomeParam, usernameParam,
				statoParam);
		if (!NumberUtils.isCreatable(idUtente)) {
			request.setAttribute("errorMessage", "Attenzione, non è stato trovato l'regista corrispondente");
			request.getRequestDispatcher("/utente/home.jsp").forward(request, response);
			return;
		}
		utenteInstance.setId(Long.parseLong(idUtente));

		try {

			// se la validazione non risulta ok
			if (!UtilityForm.validateUtenteBean(utenteInstance)) {
				request.setAttribute("edit_utente_attr", utenteInstance);
				request.setAttribute("ruoli_list_attribute", UtilityForm
						.buildCheckedRolesForPages(MyServiceFactory.getRuoloServiceInstance().listAll(), ruoliParam));
				request.setAttribute("list_stati_attr", StatoUtente.values());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/utente/edit.jsp").forward(request, response);
				return;
			}

			Map<Ruolo, Boolean> ruoli = UtilityForm.buildCheckedRolesFromRolesAlreadyInUtente(
					MyServiceFactory.getRuoloServiceInstance().listAll(), utenteInstance.getRuoli());
			// utenteInstance.setRuoli(ruoliParam);
			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
			// occupiamoci delle operazioni di business
			// utenteInstance.

			MyServiceFactory.getUtenteServiceInstance().aggiornaUtenteERuoli(utenteInstance, ruoliParam);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.setAttribute("ruoli_list_attribute", utenteInstance.getRuoli());
			request.getRequestDispatcher("/utente/home.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		// uso il sendRedirect con parametro per evitare il problema del double save on
		// refresh
		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");
	}

}
