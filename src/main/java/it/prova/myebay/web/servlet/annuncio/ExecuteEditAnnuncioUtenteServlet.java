package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/annuncio/ExecuteEditAnnuncioUtenteServlet")
public class ExecuteEditAnnuncioUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idAnnuncioParam = request.getParameter("idAnnuncio");

		String testoAnnuncioParam = request.getParameter("testoAnnuncio");
		String prezzoParam = request.getParameter("prezzo");
		String[] categoriaInputParam = request.getParameterValues("categoriaInput");

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Annuncio annuncioInstance = UtilityForm.createAnnuncioFromParams(testoAnnuncioParam, prezzoParam,
				categoriaInputParam);
		// Validazione
		if (!UtilityForm.validateAnnuncioBean(annuncioInstance) || !NumberUtils.isCreatable(idAnnuncioParam)) {
			request.setAttribute("edit_annuncio_attr", annuncioInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/annuncio/editUser.jsp").forward(request, response);
			return;
		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			annuncioInstance.setId(Long.parseLong(idAnnuncioParam));
			MyServiceFactory.getAnnuncioServiceInstance().aggiorna(annuncioInstance, categoriaInputParam);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
//			request.getRequestDispatcher("/utente/home.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/utente/home.jsp");
			return;
		}

		// andiamo ai risultati
		// request.getRequestDispatcher("/annuncio/list.jsp").forward(request,
		// response);
		response.sendRedirect(request.getContextPath() + "/utente/home.jsp");
	}

}
