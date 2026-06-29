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
}
