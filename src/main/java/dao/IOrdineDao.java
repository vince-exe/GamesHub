package dao;

import java.sql.SQLException;

import model.OrdineBean;

public interface IOrdineDao {
	public void doSave(OrdineBean ordine, int idUtente) throws SQLException;
}
