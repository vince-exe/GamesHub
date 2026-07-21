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
        PreparedStatement psUpdateStock = null;

        String insertOrdineQuery = "INSERT INTO Ordine (idUtente, totale, note, "
                + "spedizione_via, spedizione_cap, spedizione_citta, spedizione_paese, spedizione_civico, "
                + "pagamento_tipologia, pagamento_numero, pagamento_dataScadenza, pagamento_nome, pagamento_cognome, pagamento_cvc) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String insertRigaQuery = "INSERT INTO RigaOrdine (idOrdine, idProdotto, quantita, prezzoAcquisto) VALUES (?, ?, ?, ?)";

        String updateStockQuery = "UPDATE Prodotto SET disponibilita = disponibilita - ? WHERE id = ?";

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
            psUpdateStock = connection.prepareStatement(updateStockQuery);

            for(RigaOrdineBean riga : ordine.getRigheOrdine()) {
                riga.setIdOrdine(idOrdine);
                    
                psRiga.setInt(1, riga.getIdOrdine());
                psRiga.setInt(2, riga.getIdProdotto());
                psRiga.setInt(3, riga.getQuantita());
                psRiga.setBigDecimal(4, riga.getPrezzoAcquisto());
                psRiga.addBatch();

                psUpdateStock.setInt(1, riga.getQuantita());
                psUpdateStock.setInt(2, riga.getIdProdotto());
                psUpdateStock.addBatch();
            }
            
            psRiga.executeBatch();
            psUpdateStock.executeBatch();
            
            connection.commit();

        } 
        catch(SQLException e) {
        	connection.rollback();
            e.printStackTrace();
            throw e;
        } 
        finally {
            if(psOrdine != null)
            	psOrdine.close();
            if(psRiga != null)
            	psRiga.close();
            if(psUpdateStock != null)
            	psUpdateStock.close();
            
            if(connection != null) {
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
                ordine.setData(rs.getTimestamp("data"));
                
                ordini.add(ordine);
            }
        } 

        return ordini;
    }
    
    public List<OrdineBean> doRetrieveByFiltri(String dataDa, String dataA, int idUtente) throws SQLException {
        List<OrdineBean> ordini = new ArrayList<>();

        // 1=1 mi serve per concatenare gli altri filtri con gli AND così mi è comodo
        StringBuilder query = new StringBuilder("SELECT * FROM Ordine WHERE 1=1 ");
        
        boolean hasDataDa = dataDa != null && !dataDa.isEmpty();
        boolean hasDataA = dataA != null && !dataA.isEmpty();
        boolean hasIdUtente = idUtente > 0;
        
        if(hasDataDa) {
            query.append("AND data >= ?"); 
        }
        if(hasDataA) {
            query.append(" AND data <= ?"); 
        }
        if(hasIdUtente) {
            query.append(" AND idUtente = ?");
        }
        
        query.append(" ORDER BY id DESC");

        try (Connection con = ds.getConnection(); 
                PreparedStatement ps = con.prepareStatement(query.toString())) {
        	
            int paramIndex = 1;
            if (hasDataDa) {
                ps.setString(paramIndex++, dataDa + " 00:00:00");
            }
            if (hasDataA) {
                ps.setString(paramIndex++, dataA + " 23:59:59");
            }
            if (hasIdUtente) {
                ps.setInt(paramIndex++, idUtente);
            }
            try (ResultSet rs = ps.executeQuery()) {
            	while (rs.next()) {
                    OrdineBean ordine = new OrdineBean();
                    ordine.setId(rs.getInt("id")); 
                    ordine.setIdUtente(rs.getInt("idUtente"));
                    ordine.setTotale(rs.getBigDecimal("totale"));
                    ordine.setNote(rs.getString("note"));
                    ordine.setData(rs.getTimestamp("data"));

                    IndirizzoBean indirizzo = new IndirizzoBean();
                    indirizzo.setVia(rs.getString("spedizione_via"));
                    indirizzo.setCap(rs.getString("spedizione_cap"));
                    indirizzo.setCittà(rs.getString("spedizione_citta"));
                    indirizzo.setPaese(rs.getString("spedizione_paese"));
                    indirizzo.setCivico(rs.getString("spedizione_civico"));
                    ordine.setIndirizzoBean(indirizzo);
                    
                    ordini.add(ordine);
                }
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
                ordine.setData(rsOrdine.getTimestamp("data"));
                
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