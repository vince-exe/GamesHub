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

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Timestamp getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Timestamp dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}
}

