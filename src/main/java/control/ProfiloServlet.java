package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UtenteBean;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.UtenteDao;

@WebServlet("/profilo")
public class ProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/profilo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUtente = Integer.parseInt(request.getParameter("id"));
		
		UtenteDao utenteDao = new UtenteDao();
		
		UtenteBean utenteInSessione = (UtenteBean) request.getSession().getAttribute("utente");
		UtenteBean utente = new UtenteBean();
		
		utente.setId(idUtente);
		utente.setRuolo(utenteInSessione.getRuolo());
	    utente.setEmail(request.getParameter("email").toLowerCase());
	    utente.setNome(request.getParameter("nome").toLowerCase());
	    utente.setCognome(request.getParameter("cognome").toLowerCase());
	    utente.setTelefono(request.getParameter("telefono"));
	    utente.setSesso(request.getParameter("sesso"));
	    
	    String dataNascitaStr = request.getParameter("dataNascita");
        LocalDate localDate = LocalDate.parse(dataNascitaStr, formatter);
        
        LocalDateTime localDateTime = localDate.atStartOfDay();
        utente.setDataNascita(Timestamp.valueOf(localDateTime));
        
        try {
        	utenteDao.doUpdate(utente, request.getParameter("nuovaPassword"));
        	
        	request.getSession().setAttribute("utente", utente);
        	request.setAttribute("messaggio", "dati aggiornati con successo");
        }
        catch(SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore del database durante l'aggiornamento");
        }
        finally {
        	request.getRequestDispatcher("/WEB-INF/view/profilo.jsp").forward(request, response);
        }
	}
}
