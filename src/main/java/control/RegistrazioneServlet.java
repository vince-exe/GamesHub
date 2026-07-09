package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UtenteBean;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import dao.UtenteDao;

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
    public RegistrazioneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    UtenteBean utente = new UtenteBean();
	    UtenteDao utenteDao = new UtenteDao();
	    
	    utente.setEmail(request.getParameter("email").toLowerCase());
	    utente.setPassword(request.getParameter("password"));
	    utente.setNome(request.getParameter("nome").toLowerCase());
	    utente.setCognome(request.getParameter("cognome").toLowerCase());
	    utente.setTelefono(request.getParameter("cellulare"));
	    utente.setRuolo("cliente");
	    utente.setSesso(request.getParameter("genere"));
	    
	    String dataNascitaStr = request.getParameter("dataNascita");
        LocalDate localDate = LocalDate.parse(dataNascitaStr, formatter);
        
        // asStartOfDay() mi aggiunge la sezione di tempo (mezzanotte) tanto a me non interessa quando è nato precisamente
        LocalDateTime localDateTime = localDate.atStartOfDay();
        utente.setDataNascita(Timestamp.valueOf(localDateTime));
        
	    try {
	        utenteDao.doCreate(utente);	        
	        request.setAttribute("messaggio", "Registrazione completata con successo! Ora puoi accedere");	        
	    }
	    catch(SQLIntegrityConstraintViolationException e) {
	        request.setAttribute("errore", "Questa email è già utilizzata da un altro utente");

	    }
	    catch (SQLException e) {
	        request.setAttribute("errore", "Errore imprevisto sul server");
	        e.printStackTrace();
	    }
	    finally {
	        request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp").forward(request, response);
	    }
	}

}
