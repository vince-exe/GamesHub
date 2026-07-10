package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UtenteBean;

import java.io.IOException;
import java.sql.SQLException;

import dao.UtenteDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UtenteDao utenteDao = new UtenteDao();
		
		UtenteBean utente = null;
		try {
			utente = utenteDao.doRetrieveByEmail(email);
			
			if(utente == null) {
				request.setAttribute("errore", "Non esiste un utente con questa email");
				request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
				return;
			}
			
			if(utente.getPassword().equals(password)) {
				request.getSession().setAttribute("utente", utente);
				if(utente.getRuolo().equals("amministratore"))
					response.sendRedirect(request.getContextPath() + "/catalogoAdmin");
				else
					response.sendRedirect(request.getContextPath() + "/catalogo");
			}
			else {
				request.setAttribute("errore", "Credenziali errate");
			}
		} catch (SQLException e) {
			request.setAttribute("errore", "Errore imprevisto sul server");
			e.printStackTrace();
		}
	}

}
