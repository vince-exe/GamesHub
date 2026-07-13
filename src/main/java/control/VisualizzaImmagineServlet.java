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

@WebServlet("/visualizzaImmagine")
public class VisualizzaImmagineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        ProdottoDao dao = new ProdottoDao();
        
        try {
            byte[] immagineBytes = dao.getImmagineById(id); 
            
            if (immagineBytes != null && immagineBytes.length > 0) {
                response.setContentType("image/png"); 
                response.getOutputStream().write(immagineBytes);
                response.getOutputStream().flush();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
