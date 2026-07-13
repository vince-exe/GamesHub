package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoBean;

import java.io.IOException;
import java.sql.SQLException;

import dao.ProdottoDao;

@WebServlet("/visualizzaProdotto")
public class VisualizzaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDao prodottoDao = new ProdottoDao();
		int idProdotto = Integer.parseInt(request.getParameter("id"));
		
		try {
			ProdottoBean prodottoBean = prodottoDao.doRetrieveById(idProdotto);
			request.setAttribute("prodotto", prodottoBean);
		}
		catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nella visualizzazione del prodotto lato server.");
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/view/visualizzaProdotto.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
