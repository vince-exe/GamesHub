package dao;

import java.sql.SQLException;
import java.util.List;

import model.OrdineBean;

public interface IOrdineDao {
	public void doSave(OrdineBean ordine) throws SQLException;
	
	public List<OrdineBean> doRetrieveAll() throws SQLException;
	
	public List<OrdineBean> doRetrieveByFiltri(String dataDa, String dataA, int idUtente) throws SQLException;
}
