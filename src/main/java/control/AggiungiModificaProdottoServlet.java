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

import dao.ProdottoDao;
import control.UtilityForServlets;

@WebServlet("/aggiungiModificaProdotto")
@MultipartConfig // perché nel frontend utilizzo multipart/form-data
public class AggiungiModificaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!UtilityForServlets.isAdmin(request)) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		String idProdottoStr = request.getParameter("id");
		
		if(idProdottoStr == null) { // aggiungi il prodotto
			request.getRequestDispatcher("/WEB-INF/view/aggiungiModificaProdotto.jsp").forward(request, response);
			return;
		}
		// modifica prodotto
		ProdottoDao prodottoDao = new ProdottoDao();
		try {
			request.setAttribute("prodotto", prodottoDao.doRetrieveById(Integer.parseInt(idProdottoStr)));
		}
		catch (SQLException e) {
			request.setAttribute("errore", "impossibile recuperare il prodotto da modificare");
			e.printStackTrace();
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/view/aggiungiModificaProdotto.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProdottoStr = request.getParameter("id");
		int idProdotto = (idProdottoStr != null) ? Integer.parseInt(idProdottoStr) : 0;
		
		ProdottoDao prodottoDao = new ProdottoDao();
		
        try {
        	ProdottoBean prodotto = new ProdottoBean();
            
        	if(idProdottoStr != null)
        		prodotto.setId(idProdotto);
        	
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

            if(idProdottoStr != null) {
            	request.setAttribute("prodotto", prodotto);
            	prodottoDao.doUpdate(prodotto);
            }
            else
            	prodottoDao.doSave(prodotto);
            
            if(idProdottoStr != null)
            	request.setAttribute("messaggio", "Prodotto modificato correttamente");
            else
            	request.setAttribute("messaggio", "Prodotto aggiunto correttamente");
        }
        catch (SQLException e) {
        	e.printStackTrace();
            if(idProdottoStr != null) {
                request.setAttribute("errore", "Errore nell'aggiornamento del prodotto sul Database");
            } else {
                request.setAttribute("errore", "Errore nell'inserimento del prodotto nel Database"); 
            }
        } 
        finally {
            request.getRequestDispatcher("/WEB-INF/view/aggiungiModificaProdotto.jsp").forward(request, response);
        }
    }

}
