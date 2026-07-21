package dao;

import java.sql.SQLException;
import java.util.List;

import model.OrdineBean;

public interface IOrdineDao {
	public void doSave(OrdineBean ordine) throws SQLException;
	
	public List<OrdineBean> doRetrieveParzialeByFiltri(String dataDa, String dataA, int idUtente) throws SQLException;
	
	public OrdineBean doRetrieveByKey(int idOrdine) throws SQLException;
}
