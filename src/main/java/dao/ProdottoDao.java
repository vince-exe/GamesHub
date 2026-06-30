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
                   videogioco.setDisponibilità(rs.getInt("disponibilità"));
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
                   console.setDisponibilità(rs.getInt("disponibilità"));
                   console.setImmagine(rs.getBinaryStream("immagine"));
                   
                   listaConsole.add(console);
               }
           }
           return listaConsole;
	}
}
