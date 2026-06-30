package model;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProdottoBean implements Serializable {
	
	private int id;
	
	private String nome;
	
	private String descrizione;
	
	private BigDecimal prezzo;
	
	private InputStream immagine;
	
	private int disponibilità;
	
	private String tipologia;
	
	private String genere;
	
	private String piattaforma;
	
	private String chiaveAttivazione;
	
	private String casaProduttrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public InputStream getImmagine() {
		return immagine;
	}

	public void setImmagine(InputStream immagine) {
		this.immagine = immagine;
	}

	public int getDisponibilità() {
		return disponibilità;
	}

	public void setDisponibilità(int disponibilità) {
		this.disponibilità = disponibilità;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getPiattaforma() {
		return piattaforma;
	}

	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}

	public String getChiaveAttivazione() {
		return chiaveAttivazione;
	}

	public void setChiaveAttivazione(String chiaveAttivazione) {
		this.chiaveAttivazione = chiaveAttivazione;
	}

	public String getCasaProduttrice() {
		return casaProduttrice;
	}

	public void setCasaProduttrice(String casaProduttrice) {
		this.casaProduttrice = casaProduttrice;
	}
}
