package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CarrelloBean implements Serializable {

	private ArrayList<ProdottoBean> prodotti;
	
	public CarrelloBean() {
		this.prodotti = new ArrayList<ProdottoBean>();
	}
	
	public ArrayList<ProdottoBean> getProdotti() {
		return this.prodotti;
	}
	
	public void addProdotto(ProdottoBean prodotto) {
		this.prodotti.add(prodotto);
	}
}