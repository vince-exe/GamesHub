DROP DATABASE IF EXISTS GamesHubDB;
CREATE DATABASE GamesHubDB;
USE GamesHubDB;

CREATE TABLE Utente (
    id INT AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    dataNascita TIMESTAMP NOT NULL,
    sesso VARCHAR(6),
    email VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    ruolo VARCHAR(20) NOT NULL,
    telefono VARCHAR(13) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Indirizzo (
    id INT AUTO_INCREMENT,
    idUtente INT NOT NULL,
    via VARCHAR(150) NOT NULL,
    cap VARCHAR(10) NOT NULL,
    citta VARCHAR(50) NOT NULL,
    paese VARCHAR(50) NOT NULL,
    civico VARCHAR(10) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_Indirizzo_Utente FOREIGN KEY (idUtente) 
        REFERENCES Utente(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE MetodoPagamento (
    id INT AUTO_INCREMENT,
    idUtente INT NOT NULL,
    tipologia VARCHAR(50) NOT NULL,
    numero VARCHAR(30) NOT NULL,
    dataScadenza TIMESTAMP NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    cvc INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_MetodoPagamento_Utente FOREIGN KEY (idUtente) 
        REFERENCES Utente(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Prodotto (
    id INT AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descrizione VARCHAR(200),
    prezzo DECIMAL(10, 2) NOT NULL,
    immagine LONGBLOB,
    disponibilita INT NOT NULL DEFAULT 0,
    tipologia VARCHAR(50),
    genere VARCHAR(50),
    piattaforma VARCHAR(50),
    chiaveAttivazione VARCHAR(100),
    casaProduttrice VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE Ordine (
    id INT AUTO_INCREMENT,
    idUtente INT NOT NULL,
    totale INT NOT NULL,
    eta INT NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    note VARCHAR(200),
    costoSpedizione INT NOT NULL DEFAULT 0,
    stato VARCHAR(30) NOT NULL DEFAULT 'In elaborazione',
    
    -- indirizzo bean
    spedizione_via VARCHAR(150) NOT NULL,
    spedizione_cap VARCHAR(10) NOT NULL,
    spedizione_citta VARCHAR(50) NOT NULL,
    spedizione_paese VARCHAR(50) NOT NULL,
    spedizione_civico VARCHAR(10) NOT NULL,
    
    -- metodo pagamento bean
    pagamento_tipologia VARCHAR(50) NOT NULL,
    pagamento_numero VARCHAR(30) NOT NULL,
    pagamento_dataScadenza TIMESTAMP NOT NULL,
    pagamento_nome VARCHAR(50) NOT NULL,
    pagamento_cognome VARCHAR(50) NOT NULL,
    pagamento_cvc INT NOT NULL,
    
    PRIMARY KEY (id),
    CONSTRAINT FK_Ordine_Utente FOREIGN KEY (idUtente) 
        REFERENCES Utente(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE RigaOrdine (
    idOrdine INT NOT NULL,
    idProdotto INT NOT NULL,
    quantita INT NOT NULL DEFAULT 1,
    prezzoAcquisto DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (idOrdine, idProdotto),
    CONSTRAINT FK_Composizione_Ordine FOREIGN KEY (idOrdine) 
        REFERENCES Ordine(id) ON DELETE CASCADE,
    CONSTRAINT FK_Composizione_Prodotto FOREIGN KEY (idProdotto) 
        REFERENCES Prodotto(id) ON DELETE RESTRICT
);

-- AMMINISTRATORE --
INSERT INTO Utente (email, password, nome, cognome, ruolo, telefono, dataNascita, sesso) VALUES ('admin@gmail.com', 'amministratore', 'mario', 'rossi', 'amministratore', '3393089834', '2005-06-23', 'uomo');
