package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdineBean implements Serializable {
	
	private int id;
	
	private int idUtente;
	
	private BigDecimal totale;
		
	private Timestamp data;
	
	private String note;
	
	private IndirizzoBean indirizzoBean;
	
	private MetodoPagamentoBean metodoPagamentoBean;
		
	private int costoSpedizione;
	
	private String stato;

	private List<RigaOrdineBean> righeOrdine = new ArrayList<>();
	
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

	public BigDecimal getTotale() {
		return totale;
	}

	public void setTotale(BigDecimal totale) {
		this.totale = totale;
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
	
	public List<RigaOrdineBean> getRigheOrdine() {
		return righeOrdine;
	}
	
    public void setRigheOrdine(List<RigaOrdineBean> righeOrdine) {
    	this.righeOrdine = righeOrdine;
    }
}
