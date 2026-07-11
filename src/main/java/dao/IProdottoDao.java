package dao;

import java.sql.SQLException;
import java.util.List;

import model.ProdottoBean;

public interface IProdottoDao {
	public List<ProdottoBean> doRetrieveVideogiochi() throws SQLException;
	
	public List<ProdottoBean> doRetrieveConsole() throws SQLException;
	
	public List<ProdottoBean> doRetrieveAll() throws SQLException;
	
	public void doSave(ProdottoBean prodotto) throws SQLException;
} 
