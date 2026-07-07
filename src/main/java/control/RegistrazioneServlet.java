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

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegistrazioneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		UtenteBean utente = new UtenteBean();
		UtenteDao utenteDao = new UtenteDao();
		
		utente.setEmail(request.getParameter("email"));
		utente.setPassword(request.getParameter("password"));
		utente.setNome(request.getParameter("nome"));
		utente.setCognome(request.getParameter("cognome"));
		utente.setSesso(request.getParameter("sesso"));
		utente.setTelefono(request.getParameter("telefono"));
		utente.setRuolo("cliente");
		utente.setEta(Integer.parseInt(request.getParameter("eta")));
		
		try {
			utenteDao.doCreate(utente);
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (SQLException e) {
			request.setAttribute("errore", "Errore imprevisto sul server");
			e.printStackTrace();
		}
		*/
		System.out.print("Ha effettuato la POST");
	}

}
