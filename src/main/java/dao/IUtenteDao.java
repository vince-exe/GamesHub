package dao;

import java.sql.SQLException;

import model.UtenteBean;

public interface IUtenteDao {
	public UtenteBean doRetrieveByEmail(String email) throws SQLException;
	
	public void doCreate(UtenteBean utente) throws SQLException;
}
