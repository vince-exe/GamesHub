package dao;

import model.UtenteBean;

public interface IUtenteDao {
	public UtenteBean doRetrieveByEmail(String email);
}
