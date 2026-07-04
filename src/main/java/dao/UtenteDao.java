package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.UtenteBean;

public class UtenteDao implements IUtenteDao {

	private DataSource ds;
	
	public UtenteDao() {
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
	public UtenteBean doRetrieveByEmail(String email) throws SQLException {
		UtenteBean utente = null;
		String query = "SELECT * FROM Utente WHERE email = ?";
		
		try(Connection conn = this.ds.getConnection();
		    PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, email);
			
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    utente = new UtenteBean();
                    utente.setId(rs.getInt("id"));
                    utente.setEmail(rs.getString("email"));
                    utente.setPassword(rs.getString("password"));
                    utente.setNome(rs.getString("nome"));
                    utente.setCognome(rs.getString("cognome"));
                    utente.setRuolo(rs.getString("ruolo"));
                    utente.setTelefono(rs.getString("telefono"));
                    utente.setEta(rs.getInt("eta"));
                    utente.setSesso(rs.getString("sesso"));
                }
            }
		}
		
		return utente;
	}


	@Override
	public void doCreate(UtenteBean utente) throws SQLException {
		String query = "INSERT INTO utente (email, password, nome, cognome, ruolo, telefono, sesso, eta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = this.ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, utente.getEmail());
			ps.setString(2, utente.getPassword());
			ps.setString(3, utente.getNome());
			ps.setString(4, utente.getCognome());
			ps.setString(5, utente.getRuolo());
			ps.setString(6, utente.getTelefono());
			ps.setString(7, utente.getSesso());
			ps.setInt(8, utente.getEta());
			
			ps.executeUpdate();
		}	
	}

}
