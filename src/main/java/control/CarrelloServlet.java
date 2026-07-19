package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CarrelloBean;
import model.ProdottoBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProdottoDao;

@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdottoDao prodottoDao = new ProdottoDao();
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		List<CarrelloBean> carrello = (List<CarrelloBean>) session.getAttribute("carrello");

		if (carrello == null) {
			carrello = new ArrayList<>();
			session.setAttribute("carrello", carrello);
		}
		
		String idString = request.getParameter("id");
		int idProdotto = Integer.parseInt(idString);
		
		switch (action) {
		case "add":
			try {
				CarrelloBean itemEsistente = null;
				for (CarrelloBean item : carrello) {
					if (item.getProdotto().getId() == idProdotto) {
						itemEsistente = item;
						break;
					}
				}

				if (itemEsistente != null) {
					itemEsistente.setQuantita(itemEsistente.getQuantita() + 1);

					response.setStatus(HttpServletResponse.SC_OK);
					return;
				} 
				else {
					ProdottoBean prodotto = prodottoDao.doRetrieveById(idProdotto);
					
					CarrelloBean nuovoItem = new CarrelloBean(prodotto, 1);
					carrello.add(nuovoItem);

					response.setStatus(HttpServletResponse.SC_OK);
				}

			} 
			catch (SQLException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore del database.");
			}
			break;
	
		case "update":
			String quantitaString = request.getParameter("quantita");
			if (quantitaString != null && !quantitaString.isEmpty()) {
				try {
					int nuovaQuantita = Integer.parseInt(quantitaString);
					
					// Se la quantità impostata è minore o uguale a 0, ha senso rimuovere il prodotto dal carrello
					if (nuovaQuantita <= 0) {
						carrello.removeIf(item -> item.getProdotto().getId() == idProdotto);
						response.setStatus(HttpServletResponse.SC_OK);
						return;
					}
					
					// Altrimenti cerchiamo il prodotto e aggiorniamo la quantità con il nuovo valore numerico
					boolean trovato = false;
					for (CarrelloBean item : carrello) {
						if (item.getProdotto().getId() == idProdotto) {
							item.setQuantita(nuovaQuantita);
							trovato = true;
							break;
						}
					}
					
					if (trovato) {
						response.setStatus(HttpServletResponse.SC_OK);
					} else {
						response.sendError(HttpServletResponse.SC_NOT_FOUND, "Prodotto non presente nel carrello.");
					}
					
				} catch (NumberFormatException e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantità non valida.");
				}
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parametro quantità mancante.");
			}
			break;
		
		case "delete":
			CarrelloBean itemDaRimuovere = null;
			for (CarrelloBean item : carrello) {
				if (item.getProdotto().getId() == idProdotto) {
					itemDaRimuovere = item;
					break;
				}
			}
			
			if (itemDaRimuovere != null) {
				carrello.remove(itemDaRimuovere);
				response.setStatus(HttpServletResponse.SC_OK);
			} 
			else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			break;
		}
	}
}
