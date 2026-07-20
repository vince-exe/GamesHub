package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
    public List<OrdineBean> doRetrieveAll() throws SQLException {
        List<OrdineBean> ordini = new ArrayList<>();
        
        String query = "SELECT * FROM Ordine ORDER BY id DESC";
        
        try (Connection con = ds.getConnection(); 
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                OrdineBean ordine = new OrdineBean();
                ordine.setId(rs.getInt("id")); 
                ordine.setIdUtente(rs.getInt("idUtente"));
                ordine.setTotale(rs.getBigDecimal("totale"));
                ordine.setNote(rs.getString("note"));

                IndirizzoBean indirizzo = new IndirizzoBean();
                indirizzo.setVia(rs.getString("spedizione_via"));
                indirizzo.setCap(rs.getString("spedizione_cap"));
                indirizzo.setCittà(rs.getString("spedizione_citta"));
                indirizzo.setPaese(rs.getString("spedizione_paese"));
                indirizzo.setCivico(rs.getString("spedizione_civico"));
                ordine.setIndirizzoBean(indirizzo);

                MetodoPagamentoBean metodo = new MetodoPagamentoBean();
                metodo.setTipologia(rs.getString("pagamento_tipologia"));
                metodo.setNumero(rs.getString("pagamento_numero"));
                metodo.setDataScadenza(rs.getTimestamp("pagamento_dataScadenza"));
                metodo.setNome(rs.getString("pagamento_nome"));
                metodo.setCognome(rs.getString("pagamento_cognome"));
                metodo.setCvc(rs.getInt("pagamento_cvc"));
                ordine.setMetodoPagamentoBean(metodo);

                ordini.add(ordine);
            }
        } 

        return ordini;
    }
    
    public List<OrdineBean> doRetrieveByFiltri(String dataDa, String dataA, Integer idUtente) throws SQLException {
        List<OrdineBean> ordini = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM Ordine");
        
        if (dataDa != null && !dataDa.isEmpty()) {
            query.append("WHERE pagamento_dataScadenza >= ?"); 
        }
        if (dataA != null && !dataA.isEmpty()) {
            query.append(" AND pagamento_dataScadenza <= ?"); 
        }
        if (idUtente != null && idUtente > 0) {
            query.append(" AND idUtente = ?");
        }
        
        query.append(" ORDER BY id DESC");

        try (Connection con = ds.getConnection(); 
                PreparedStatement ps = con.prepareStatement(query.toString());
                ResultSet rs = ps.executeQuery()) {
        	
            int paramIndex = 1;
            if (dataDa != null && !dataDa.isEmpty()) {
                ps.setString(paramIndex++, dataDa + " 00:00:00");
            }
            if (dataA != null && !dataA.isEmpty()) {
                ps.setString(paramIndex++, dataA + " 23:59:59");
            }
            if (idUtente != null && idUtente > 0) {
                ps.setInt(paramIndex++, idUtente);
            }

            while (rs.next()) {
                OrdineBean ordine = new OrdineBean();
                ordine.setId(rs.getInt("id")); 
                ordine.setIdUtente(rs.getInt("idUtente"));
                ordine.setTotale(rs.getBigDecimal("totale"));
                ordine.setNote(rs.getString("note"));

                IndirizzoBean indirizzo = new IndirizzoBean();
                indirizzo.setVia(rs.getString("spedizione_via"));
                indirizzo.setCap(rs.getString("spedizione_cap"));
                indirizzo.setCittà(rs.getString("spedizione_citta"));
                indirizzo.setPaese(rs.getString("spedizione_paese"));
                indirizzo.setCivico(rs.getString("spedizione_civico"));
                ordine.setIndirizzoBean(indirizzo);

                MetodoPagamentoBean metodo = new MetodoPagamentoBean();
                metodo.setTipologia(rs.getString("pagamento_tipologia"));
                metodo.setNumero(rs.getString("pagamento_numero"));
                metodo.setDataScadenza(rs.getTimestamp("pagamento_dataScadenza"));
                metodo.setNome(rs.getString("pagamento_nome"));
                metodo.setCognome(rs.getString("pagamento_cognome"));
                metodo.setCvc(rs.getInt("pagamento_cvc"));
                ordine.setMetodoPagamentoBean(metodo);

                ordini.add(ordine);
            }
        } 
        
        return ordini;
    }

    public OrdineBean doRetrieveByKey(int idOrdine) throws SQLException {
        Connection connection = null;
        PreparedStatement psOrdine = null;
        PreparedStatement psRighe = null;
        OrdineBean ordine = null;
        
        try {
            connection = ds.getConnection();
            
            psOrdine = connection.prepareStatement("SELECT * FROM Ordine WHERE id = ?");
            psOrdine.setInt(1, idOrdine);
            ResultSet rsOrdine = psOrdine.executeQuery();
            
            if (rsOrdine.next()) {
                ordine = new OrdineBean();
                ordine.setId(rsOrdine.getInt("id")); 
                ordine.setIdUtente(rsOrdine.getInt("idUtente"));
                ordine.setTotale(rsOrdine.getBigDecimal("totale"));
                ordine.setNote(rsOrdine.getString("note"));

                IndirizzoBean indirizzo = new IndirizzoBean();
                indirizzo.setVia(rsOrdine.getString("spedizione_via"));
                indirizzo.setCap(rsOrdine.getString("spedizione_cap"));
                indirizzo.setCittà(rsOrdine.getString("spedizione_citta"));
                indirizzo.setPaese(rsOrdine.getString("spedizione_paese"));
                indirizzo.setCivico(rsOrdine.getString("spedizione_civico"));
                ordine.setIndirizzoBean(indirizzo);

                MetodoPagamentoBean metodo = new MetodoPagamentoBean();
                metodo.setTipologia(rsOrdine.getString("pagamento_tipologia"));
                metodo.setNumero(rsOrdine.getString("pagamento_numero"));
                metodo.setDataScadenza(rsOrdine.getTimestamp("pagamento_dataScadenza"));
                metodo.setNome(rsOrdine.getString("pagamento_nome"));
                metodo.setCognome(rsOrdine.getString("pagamento_cognome"));
                metodo.setCvc(rsOrdine.getInt("pagamento_cvc"));
                ordine.setMetodoPagamentoBean(metodo);
                
                psRighe = connection.prepareStatement("SELECT * FROM RigaOrdine WHERE idOrdine = ?");
                psRighe.setInt(1, idOrdine);
                ResultSet rsRighe = psRighe.executeQuery();
                
                List<RigaOrdineBean> righe = new ArrayList<>();
                while (rsRighe.next()) {
                    RigaOrdineBean riga = new RigaOrdineBean();
                    riga.setIdOrdine(rsRighe.getInt("idOrdine"));
                    riga.setIdProdotto(rsRighe.getInt("idProdotto"));
                    riga.setQuantita(rsRighe.getInt("quantita"));
                    riga.setPrezzoAcquisto(rsRighe.getBigDecimal("prezzoAcquisto"));
                    righe.add(riga);
                }
                ordine.setRigheOrdine(righe);
            }
        } 
        finally {
            if (psOrdine != null) psOrdine.close();
            if (psRighe != null) psRighe.close();
            if (connection != null) connection.close();
        }
        
        return ordine;
    }
}