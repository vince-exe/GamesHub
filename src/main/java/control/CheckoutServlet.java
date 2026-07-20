package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CarrelloBean;
import model.IndirizzoBean;
import model.MetodoPagamentoBean;
import model.OrdineBean;
import model.RigaOrdineBean;
import model.UtenteBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.OrdineDao;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<CarrelloBean> carrello = (List<CarrelloBean>) session.getAttribute("carrello");		

		if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/carrello");
            return;
        }

		request.getRequestDispatcher("/WEB-INF/view/checkout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UtenteBean utenteSessione = (UtenteBean) session.getAttribute("utente");
		List<CarrelloBean> carrello = (List<CarrelloBean>) session.getAttribute("carrello");
				
		try {
            IndirizzoBean indirizzo = new IndirizzoBean();
            indirizzo.setIdUtente(utenteSessione.getId());
            indirizzo.setVia(request.getParameter("via"));
            indirizzo.setCivico(request.getParameter("civico"));
            indirizzo.setCittà(request.getParameter("citta"));
            indirizzo.setCap(request.getParameter("cap"));
            indirizzo.setPaese(request.getParameter("paese"));

            MetodoPagamentoBean metodo = new MetodoPagamentoBean();
            metodo.setIdUtente(utenteSessione.getId());
            metodo.setTipologia(request.getParameter("tipologiaPagamento"));
            metodo.setNumero(request.getParameter("numeroCarta"));
            metodo.setNome(request.getParameter("nomeTitolare"));
            metodo.setCognome(request.getParameter("cognomeTitolare"));
            metodo.setCvc(Integer.parseInt(request.getParameter("cvc")));

    	    String scadenzaStr = request.getParameter("dataScadenza");
            LocalDate localDate = LocalDate.parse(scadenzaStr, formatter);
            
            LocalDateTime localDateTime = localDate.atStartOfDay();
            metodo.setDataScadenza(Timestamp.valueOf(localDateTime));
            
            OrdineBean ordine = new OrdineBean();
            ordine.setIdUtente(utenteSessione.getId());
            ordine.setNote(request.getParameter("note"));
            ordine.setIndirizzoBean(indirizzo);
            ordine.setMetodoPagamentoBean(metodo);

            BigDecimal totaleComplessivo = BigDecimal.ZERO;
            List<RigaOrdineBean> listaRighe = new ArrayList<>();

            for (CarrelloBean item : carrello) {
                RigaOrdineBean riga = new RigaOrdineBean();
                riga.setIdProdotto(item.getProdotto().getId());
                riga.setQuantita(item.getQuantita());
                riga.setPrezzoAcquisto(item.getProdotto().getPrezzo());
                
                BigDecimal subtotale = riga.getPrezzoAcquisto().multiply(BigDecimal.valueOf(riga.getQuantita()));
                
                totaleComplessivo = totaleComplessivo.add(subtotale);
                listaRighe.add(riga);
            }
            
            ordine.setTotale(totaleComplessivo);
            ordine.setRigheOrdine(listaRighe);

            OrdineDao ordineDao = new OrdineDao();
            ordineDao.doSave(ordine);

            session.removeAttribute("carrello");
            request.setAttribute("messaggio", "Ordine effettuato con successo!");
            request.getRequestDispatcher("/WEB-INF/view/checkout.jsp").forward(request, response);
        } 
		catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nella transazione sul database.");
        } 
		catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "I dati del modulo non sono validi.");
        }
    }
}