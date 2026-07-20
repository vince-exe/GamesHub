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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/carrello.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				for(CarrelloBean item : carrello) {
					if(item.getProdotto().getId() == idProdotto) {
						itemEsistente = item;
						break;
					}
				}

				if(itemEsistente != null) {
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
			try {
				int nuovaQuantita = Integer.parseInt(quantitaString);
				
				if (nuovaQuantita <= 0) {
					carrello.removeIf(item -> item.getProdotto().getId() == idProdotto);
				} 
				else {
					for (CarrelloBean item : carrello) {
						if (item.getProdotto().getId() == idProdotto) {
							item.setQuantita(nuovaQuantita);
							break;
						}
					}
				}
				
				StringBuilder jsonStr = new StringBuilder("[");
				for (int i = 0; i < carrello.size(); i++) {
					CarrelloBean item = carrello.get(i);
					ProdottoBean p = item.getProdotto();
					double subtotale = p.getPrezzo().doubleValue() * item.getQuantita();
					
					jsonStr.append("{\"id\":").append(p.getId())
						   .append(", \"nome\":\"").append(p.getNome().replace("\"", "\\\""))
						   .append("\", \"prezzo\":").append(String.format(java.util.Locale.US, "%.2f", p.getPrezzo().doubleValue()))
						   .append(", \"quantita\":").append(item.getQuantita())
						   .append(", \"subtotale\":").append(String.format(java.util.Locale.US, "%.2f", subtotale))
						   .append("}");
					
					if (i < carrello.size() - 1) {
						jsonStr.append(","); 
					}
				}
				jsonStr.append("]");

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(jsonStr.toString());
				response.setStatus(HttpServletResponse.SC_OK);
			} 
			catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			break;
		
		case "delete":
			CarrelloBean itemDaRimuovere = null;
			for (CarrelloBean item : carrello) {
				if(item.getProdotto().getId() == idProdotto) {
					itemDaRimuovere = item;
					break;
				}
			}
			
			if(itemDaRimuovere != null) {
				carrello.remove(itemDaRimuovere);
				
				StringBuilder jsonStr = new StringBuilder("[");
				for (int i = 0; i < carrello.size(); i++) {
					CarrelloBean item = carrello.get(i);
					ProdottoBean p = item.getProdotto();
					double subtotale = p.getPrezzo().doubleValue() * item.getQuantita();
					
					jsonStr.append("{\"id\":").append(p.getId())
						   .append(", \"nome\":\"").append(p.getNome().replace("\"", "\\\""))
						   .append("\", \"prezzo\":").append(String.format(java.util.Locale.US, "%.2f", p.getPrezzo().doubleValue()))
						   .append(", \"quantita\":").append(item.getQuantita())
						   .append(", \"subtotale\":").append(String.format(java.util.Locale.US, "%.2f", subtotale))
						   .append("}");
					
					if (i < carrello.size() - 1) {
						jsonStr.append(","); 
					}
				}
				jsonStr.append("]");

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(jsonStr.toString());
				response.setStatus(HttpServletResponse.SC_OK);
			} 
			else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			break;
		}
	}
}