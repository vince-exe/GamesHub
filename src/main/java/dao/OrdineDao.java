package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.IndirizzoBean;
import model.MetodoPagamentoBean;
import model.OrdineBean;
import model.RigaOrdineBean;

public class OrdineDao implements IOrdineDao {
	
	private DataSource ds;
	
	public OrdineDao() {
		try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            this.ds = (DataSource) envContext.lookup("jdbc/storageDB"); 
		}
		catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void doSave(OrdineBean ordine) throws SQLException {
        Connection connection = null;
        PreparedStatement psOrdine = null;
        PreparedStatement psRiga = null;

        String insertOrdineQuery = "INSERT INTO Ordine (idUtente, totale, note, "
                + "spedizione_via, spedizione_cap, spedizione_citta, spedizione_paese, spedizione_civico, "
                + "pagamento_tipologia, pagamento_numero, pagamento_dataScadenza, pagamento_nome, pagamento_cognome, pagamento_cvc) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String insertRigaQuery = "INSERT INTO RigaOrdine (idOrdine, idProdotto, quantita, prezzoAcquisto) VALUES (?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);

            psOrdine = connection.prepareStatement(insertOrdineQuery, Statement.RETURN_GENERATED_KEYS);
            psOrdine.setInt(1, ordine.getIdUtente());
            psOrdine.setBigDecimal(2, ordine.getTotale());
            psOrdine.setString(3, ordine.getNote());

            IndirizzoBean indirizzo = ordine.getIndirizzoBean();
            psOrdine.setString(4, indirizzo.getVia());
            psOrdine.setString(5, indirizzo.getCap());
            psOrdine.setString(6, indirizzo.getCittà());
            psOrdine.setString(7, indirizzo.getPaese());
            psOrdine.setString(8, indirizzo.getCivico());

            MetodoPagamentoBean metodo = ordine.getMetodoPagamentoBean();
            psOrdine.setString(9, metodo.getTipologia());
            psOrdine.setString(10, metodo.getNumero());
            psOrdine.setTimestamp(11, metodo.getDataScadenza());
            psOrdine.setString(12, metodo.getNome());
            psOrdine.setString(13, metodo.getCognome());
            psOrdine.setInt(14, metodo.getCvc());

            psOrdine.executeUpdate();

            ResultSet rs = psOrdine.getGeneratedKeys();
            int idOrdine = 0;
            if(rs.next()) {
                idOrdine = rs.getInt(1);
                ordine.setId(idOrdine);
            }
            psRiga = connection.prepareStatement(insertRigaQuery);
                
            for(RigaOrdineBean riga : ordine.getRigheOrdine()) {
            	riga.setIdOrdine(idOrdine);
                    
                psRiga.setInt(1, riga.getIdOrdine());
                psRiga.setInt(2, riga.getIdProdotto());
                psRiga.setInt(3, riga.getQuantita());
                psRiga.setBigDecimal(4, riga.getPrezzoAcquisto());
                    
                psRiga.addBatch();
            }
            
            psRiga.executeBatch();
            
            connection.commit();

        } 
        catch(SQLException e) {
        	connection.rollback();
        	e.printStackTrace();
            throw e;
        } 
        finally {
            if (psOrdine != null)
            	psOrdine.close();
            
            if (psRiga != null)
            	psRiga.close();
            
            if (connection != null) {
            	connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
}