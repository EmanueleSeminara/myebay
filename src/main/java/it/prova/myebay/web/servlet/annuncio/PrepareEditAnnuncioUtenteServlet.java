package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/annuncio/PrepareEditAnnuncioUtenteServlet")
public class PrepareEditAnnuncioUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroIdDellAnnuncioDaEliminare = request.getParameter("idAnnuncio");

		if (!NumberUtils.isCreatable(parametroIdDellAnnuncioDaEliminare)) {
			request.setAttribute("errorMessage", "Attenzione, non è stato trovato l'annuncio corrispondente");
			request.getRequestDispatcher("/utente/home.jsp").forward(request, response);
			return;
		}

		try {

			request.setAttribute("edit_annuncio_attr", MyServiceFactory.getAnnuncioServiceInstance()
					.caricaSingoloElementoEager(Long.parseLong(parametroIdDellAnnuncioDaEliminare)));
			request.setAttribute("categorie_list", MyServiceFactory.getCategoriaServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/utente/home.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/annuncio/editUser.jsp").forward(request, response);
	}

}
