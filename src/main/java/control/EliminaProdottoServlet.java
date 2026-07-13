package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import dao.ProdottoDao;

@WebServlet("/eliminaProdotto")
public class EliminaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EliminaProdottoServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProdottoStr = request.getParameter("id");
		
		if(idProdottoStr == null || idProdottoStr.isEmpty()) {
			request.setAttribute("errore", "id prodotto invalido");
			response.sendRedirect(request.getContextPath() + "/catalogoAdmin");
			return;
		}
		
		ProdottoDao prodottoDao = new ProdottoDao();
		int idProdotto = Integer.parseInt(request.getParameter("id"));
		
		try {
			prodottoDao.doDelete(idProdotto);
		}
		catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nella rimozione del prodotto lato server.");
		}
		finally {
			response.sendRedirect(request.getContextPath() + "/catalogoAdmin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
