package model;

import java.io.Serializable;
import java.sql.Timestamp;

import model.enums.TipologiaMetodoPagamento;

public class MetodoPagamentoBean implements Serializable {

	private int id;
	
	private int idUtente;
	
	private String tipologia;
	
	private String numero;
	
	private Timestamp dataScadenza;
	
	private String nome;
	
	private String cognome;
	
	private int cvc;
}

