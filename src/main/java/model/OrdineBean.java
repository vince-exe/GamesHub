package model;

import java.io.Serializable;
import java.sql.Timestamp;

import model.enums.StatoOrdine;

public class OrdineBean implements Serializable {
	
	private int id;
	
	private int idUtente;
	
	private int totale;
	
	private int eta;
	
	private Timestamp data;
	
	private String note;
	
	private IndirizzoBean indirizzoBean;
	
	private MetodoPagamentoBean metodoPagamentoBean;
		
	private int costoSpedizione;
	
	private String stato;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public int getTotale() {
		return totale;
	}

	public void setTotale(int totale) {
		this.totale = totale;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public IndirizzoBean getIndirizzoBean() {
		return indirizzoBean;
	}

	public void setIndirizzoBean(IndirizzoBean indirizzoBean) {
		this.indirizzoBean = indirizzoBean;
	}

	public MetodoPagamentoBean getMetodoPagamentoBean() {
		return metodoPagamentoBean;
	}

	public void setMetodoPagamentoBean(MetodoPagamentoBean metodoPagamentoBean) {
		this.metodoPagamentoBean = metodoPagamentoBean;
	}

	public int getCostoSpedizione() {
		return costoSpedizione;
	}

	public void setCostoSpedizione(int costoSpedizione) {
		this.costoSpedizione = costoSpedizione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
}
