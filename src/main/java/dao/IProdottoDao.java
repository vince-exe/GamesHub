package dao;

import java.sql.SQLException;
import java.util.List;

import model.ProdottoBean;

public interface IProdottoDao {	
	public List<ProdottoBean> doRetrieveAll() throws SQLException;
	
	public List<ProdottoBean> doRetrieveOnTipologia(String tipologia) throws SQLException;
	
	public ProdottoBean doRetrieveById(int id) throws SQLException;
	
	public byte[] getImmagineById(int id) throws SQLException;
	
	public void doSave(ProdottoBean prodotto) throws SQLException;
	
	public void doDelete(int idProdotto) throws SQLException;
	
	public void doUpdate(ProdottoBean prodotto) throws SQLException;
} 
