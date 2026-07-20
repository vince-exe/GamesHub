package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.OrdineBean;

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
	
	@Override
	public void doSave(OrdineBean ordine, int idUtente) throws SQLException {
		String query = "INSERT INTO ordine (idUtente, totale, eta, data, note, idIndirizzo, idMetodoPagamento, costoSpedizione, stato) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = this.ds.getConnection();
			    PreparedStatement ps = conn.prepareStatement(query)) {
		
			ps.setInt(1, ordine.getIdUtente());
	        ps.setInt(2, ordine.getTotale());
	        ps.setInt(3, ordine.getEta());
	        ps.setTimestamp(4, ordine.getData());
	        ps.setString(5, ordine.getNote());
	        
	        ps.setInt(6, ordine.getIndirizzoBean().getId());
	        ps.setInt(7, ordine.getMetodoPagamentoBean().getId());
	        
	        ps.setInt(8, ordine.getCostoSpedizione());
	        ps.setString(9, ordine.getStato());

	        ps.executeUpdate();
		}
	}

}
