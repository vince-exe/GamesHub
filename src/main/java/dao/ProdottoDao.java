package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.ProdottoBean;

public class ProdottoDao implements IProdottoDao {

	private DataSource ds;
	
	public ProdottoDao() {
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
	public List<ProdottoBean> doRetrieveVideogiochi() throws SQLException {
		List<ProdottoBean> videogiochi = new ArrayList<>();
		
		String query = "SELECT * FROM prodotto WHERE tipologia = 'videogioco'";
		
        try (Connection con = ds.getConnection(); 
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

               while (rs.next()) {
                   ProdottoBean videogioco = new ProdottoBean();
                   videogioco.setId(rs.getInt("id"));
                   videogioco.setNome(rs.getString("nome"));
                   videogioco.setDescrizione(rs.getString("descrizione"));
                   videogioco.setPrezzo(rs.getBigDecimal("prezzo"));
                   videogioco.setTipologia(rs.getString("tipologia"));
                   videogioco.setGenere(rs.getString("genere"));
                   videogioco.setPiattaforma(rs.getString("piattaforma"));
                   videogioco.setChiaveAttivazione(rs.getString("chiaveAttivazione"));
                   videogioco.setCasaProduttrice(rs.getString("casaProduttrice"));
                   videogioco.setDisponibilità(rs.getInt("disponibilita"));
                   videogioco.setImmagine(rs.getBinaryStream("immagine"));
                   
                   videogiochi.add(videogioco);
               }
           }
           return videogiochi;
	}

	@Override
	public List<ProdottoBean> doRetrieveConsole() throws SQLException {
		List<ProdottoBean> listaConsole = new ArrayList<>();
		
		String query = "SELECT * FROM prodotto WHERE tipologia = 'console'";
		
        try (Connection con = ds.getConnection(); 
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

               while (rs.next()) {
                   ProdottoBean console = new ProdottoBean();
                   console.setId(rs.getInt("id"));
                   console.setNome(rs.getString("nome"));
                   console.setDescrizione(rs.getString("descrizione"));
                   console.setPrezzo(rs.getBigDecimal("prezzo"));
                   console.setTipologia(rs.getString("tipologia"));
                   console.setCasaProduttrice(rs.getString("casaProduttrice"));
                   console.setDisponibilità(rs.getInt("disponibilita"));
                   console.setImmagine(rs.getBinaryStream("immagine"));
                   
                   listaConsole.add(console);
               }
           }
           return listaConsole;
	}

	@Override
	public List<ProdottoBean> doRetrieveAll() throws SQLException {
		List<ProdottoBean> listaProdotti = new ArrayList<>();

		String query = "SELECT * FROM prodotto";

		
        try (Connection con = ds.getConnection(); 
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

               while (rs.next()) {
                   ProdottoBean prodotto = new ProdottoBean();
                   prodotto.setId(rs.getInt("id"));
                   prodotto.setNome(rs.getString("nome"));
                   prodotto.setDescrizione(rs.getString("descrizione"));
                   prodotto.setPrezzo(rs.getBigDecimal("prezzo"));
                   prodotto.setTipologia(rs.getString("tipologia"));
                   prodotto.setCasaProduttrice(rs.getString("casaProduttrice"));
                   prodotto.setDisponibilità(rs.getInt("disponibilita"));
                   prodotto.setImmagine(rs.getBinaryStream("immagine"));
                   prodotto.setGenere(rs.getString("genere"));
                   prodotto.setChiaveAttivazione(rs.getString("chiaveAttivazione"));
                   prodotto.setPiattaforma(rs.getString("piattaforma"));
                   
                   listaProdotti.add(prodotto);
               }
           }
           return listaProdotti;
	}

	@Override
	public void doSave(ProdottoBean prodotto) throws SQLException {
	    String query = "INSERT INTO Prodotto (nome, descrizione, prezzo, immagine, disponibilita, tipologia, genere, piattaforma, chiaveAttivazione, casaProduttrice) "
	                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection con = ds.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        
	        ps.setString(1, prodotto.getNome());
	        ps.setString(2, prodotto.getDescrizione());
	        ps.setBigDecimal(3, prodotto.getPrezzo());
	        
	        if (prodotto.getImmagine() != null) {
	            ps.setBinaryStream(4, prodotto.getImmagine());
	        } else {
	            ps.setNull(4, java.sql.Types.BLOB);
	        }
	        
	        ps.setInt(5, prodotto.getDisponibilità());
	        ps.setString(6, prodotto.getTipologia());
	        
	        // questi sono campi opzionali in base alla tipologia del prodotto
	        ps.setString(7, prodotto.getGenere());
	        ps.setString(8, prodotto.getPiattaforma());
	        ps.setString(9, prodotto.getChiaveAttivazione());
	        ps.setString(10, prodotto.getCasaProduttrice());

	        ps.executeUpdate();
	    }
	}
}
