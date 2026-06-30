package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ProdottoDao;

@WebServlet("/console")
public class ConsoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDao prodottoDao = new ProdottoDao();
		try {
			List<ProdottoBean> listaConsole;
			listaConsole = prodottoDao.doRetrieveVideogiochi();
			
			request.setAttribute("catalogoConsole", listaConsole);
		} catch (SQLException e) {
			request.setAttribute("errore", "Errore nel caricamento del catalogo console.");
		}
		
		request.getRequestDispatcher("/WEB-INF/view/console.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
