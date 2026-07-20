package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UtenteBean;

import java.io.IOException;
import java.sql.SQLException;

import dao.OrdineDao;

@WebServlet("/ordini")
public class OrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdineDao ordiniDao = new OrdineDao();
		HttpSession session = request.getSession();
		
		UtenteBean utenteSessione = (UtenteBean) session.getAttribute("utente");
		
		try {
			if(utenteSessione.getRuolo().equals("amministratore")) {
				request.setAttribute("ordini", ordiniDao.doRetrieveAll());
			}
			else {
				request.setAttribute("ordini", ordiniDao.doRetrieveByFiltri(null, null, utenteSessione.getId()));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errore", "impossibile recuperare gli ordini dal database");
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/view/ordini.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
