package model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RigaOrdineBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int idOrdine;
    private int idProdotto;
    private int quantita;
    private BigDecimal prezzoAcquisto;

    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getIdOrdine() {
    	return idOrdine;
    }
    
    public void setIdOrdine(int idOrdine) {
    	this.idOrdine = idOrdine;
    }

    public int getIdProdotto() {
    	return idProdotto;
    }
    
    public void setIdProdotto(int idProdotto) {
    	this.idProdotto = idProdotto;
    }

    public int getQuantita() {
    	return quantita;
    }
    
    public void setQuantita(int quantita) {
    	this.quantita = quantita;
    }

    public BigDecimal getPrezzoAcquisto() {
    	return prezzoAcquisto;
    
    }
    
    public void setPrezzoAcquisto(BigDecimal prezzoAcquisto) {
    	this.prezzoAcquisto = prezzoAcquisto;
    }
}