package model;

import java.io.InputStream;
import java.io.Serializable;

public class ProdottoBean implements Serializable {
	
	private int id;
	
	private String nome;
	
	private String descrizione;
	
	private float prezzo;
	
	private InputStream immagine;
	
	private int disponibilità;
	
	private String tipologia;
	
	private String genere;
	
	private String piattaforma;
	
	private String chiaveAttivazione;
	
	private String casaProduttrice;
}
