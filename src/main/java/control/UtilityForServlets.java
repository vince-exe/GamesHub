package control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.UtenteBean;

public class UtilityForServlets {
	
	public static boolean isAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if(session == null)
			return false;
		
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
	
		return utente != null && "amministratore".equalsIgnoreCase(utente.getRuolo());
	}
}
