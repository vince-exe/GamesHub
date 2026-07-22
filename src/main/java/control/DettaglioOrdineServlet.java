package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.OrdineDao;

@WebServlet("/dettaglioOrdine")
public class DettaglioOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idOrdine = Integer.parseInt(request.getParameter("id"));
		OrdineDao ordineDao = new OrdineDao();
		
		try {
			request.setAttribute("ordine", ordineDao.doRetrieveByKey(idOrdine));
			request.getRequestDispatcher("/WEB-INF/view/dettaglioOrdine.jsp").forward(request, response);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
