package it.prova.myebay.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;

@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter implements Filter {

	private static final String[] EXCLUDED_URLS = { "/ExecuteLoginServlet", "/ExecuteLogoutServlet",
			"/ExecuteRegisterServlet", "/PrepareLoginServlet", "/PrepareRegisterServlet", "/assets", "index.jsp",
			"login.jsp", "register.jsp", "/ExecuteSearchAnnuncioServlet", "/annuncio/list.jsp",
			"ExecuteVisualizzaAnnuncioServlet", "/annuncio/show.jsp" };
	private static final String[] ADMIN_URLS = { "/utente" };
	private static final String[] CLASSIC_USER_URLS = {};

	public CheckAuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// prendo il path della request che sta passando in questo momento
		String pathAttuale = httpRequest.getServletPath();

		// vediamo se il path risulta tra quelli 'liberi di passare'
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);

		// se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isInWhiteList) {
			Utente utenteInSession = (Utente) httpRequest.getSession().getAttribute("userInfo");
			// intanto verifico se utente in sessione
			if (utenteInSession == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath());
				return;
			}
//			
//			if (isPathForOnlyClassicUsers(pathAttuale)) {
//				httpRequest.setAttribute("errorMessage", "Non si è autorizzati alla navigazione richiesta");
//				httpRequest.getRequestDispatcher("/home").forward(httpRequest, httpResponse);
//				return;
//			}
			// controllo che utente abbia ruolo admin se nel path risulta presente /admin/
			if (isPathForOnlyAdministrators(pathAttuale) && !utenteInSession.isAdmin()) {
				httpRequest.setAttribute("errorMessage", "Non si è autorizzati alla navigazione richiesta");
				// httpResponse.sendRedirect(httpRequest.getContextPath());
				httpRequest.getRequestDispatcher("/").forward(httpRequest, httpResponse);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean isPathInWhiteList(String requestPath) {
		// bisogna controllare che se il path risulta proprio "" oppure se
		// siamo in presenza un url 'libero'
		if (requestPath.equals("")) {
			return true;
		}

		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : ADMIN_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyClassicUsers(String requestPath) {
		for (String urlPatternItem : CLASSIC_USER_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	public void destroy() {
	}

}
