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

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		if(filtro == null)
			filtro = "tutti";
		
		ProdottoDao prodottoDao = new ProdottoDao();
		List<ProdottoBean> prodotti = null;
		
		try {
			switch(filtro) {
			case "console":
				prodotti = prodottoDao.doRetrieveOnTipologia("console");
				break;
				
			case "videogioco":
				prodotti = prodottoDao.doRetrieveOnTipologia("videogioco");
				break;
			
			default: // tutti
				prodotti = prodottoDao.doRetrieveAll();
				break;
			}			
			request.setAttribute("catalogo", prodotti);
			
		} catch (SQLException e) {
			request.setAttribute("errore", "Errore nel prendere i prodotti del catalogo");
			request.getRequestDispatcher("/WEB-INF/view/catalogo.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/view/catalogo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
