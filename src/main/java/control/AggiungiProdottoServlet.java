package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import dao.ProdottoDao;

@WebServlet("/aggiungiProdotto")
@MultipartConfig // perché nel frontend utilizzo multipart/form-data
public class AggiungiProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/aggiungiProdotto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDao prodottoDao = new ProdottoDao();
		
        try {
        	ProdottoBean prodotto = new ProdottoBean();
                    
            prodotto.setNome(request.getParameter("nome").toLowerCase());
            prodotto.setDescrizione(request.getParameter("descrizione").toLowerCase());
            prodotto.setCasaProduttrice(request.getParameter("casaProduttrice").toLowerCase());
            prodotto.setTipologia(request.getParameter("tipologia").toLowerCase());
            prodotto.setPrezzo(new BigDecimal(request.getParameter("prezzo")));
            prodotto.setDisponibilità(Integer.parseInt(request.getParameter("disponibilita")));
            
            // gestisco i parametri opzionali basati sulla tipologia
            if ("videogioco".equals(prodotto.getTipologia())) {
                prodotto.setGenere(request.getParameter("genere").toLowerCase());
                prodotto.setPiattaforma(request.getParameter("piattaforma").toLowerCase());
                prodotto.setChiaveAttivazione(request.getParameter("chiaveAttivazione").toUpperCase());
            } else {
                prodotto.setGenere(null);
                prodotto.setPiattaforma(null);
                prodotto.setChiaveAttivazione(null);
            }

            jakarta.servlet.http.Part filePart = request.getPart("immagine");
            if (filePart != null && filePart.getSize() > 0) {
                prodotto.setImmagine(filePart.getInputStream());
            }

            prodottoDao.doSave(prodotto);
            request.setAttribute("messaggio", "Prodotto salvato correttamente");
        }
        catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nel salvataggio del prodotto sul Database");
        } 
        finally {
        	request.getRequestDispatcher("/WEB-INF/view/aggiungiProdotto.jsp").forward(request, response);
        }
    }

}
