# GamesHub
A project for my university course "Tecnology of Web Development"

# Guida all'Avvio e Configurazione
---

## Prerequisiti
Prima di iniziare, assicurati di aver installato i seguenti software sul tuo sistema:

1. **Java Development Kit (JDK)**: JDK 17 o superiore
2. **Eclipse IDE**: *Eclipse IDE for Enterprise Java and Web Developers*.
3. **Apache Tomcat 11**: Application server / Servlet Container.
4. **MySQL Community Server**: DBMS relazionale.
5. **MySQL Workbench**: Interfaccia grafica per la gestione del database MySQL.

---

## Step 1: Configurazione del Database (MySQL Workbench)

1. Copia il codice dello script init.sql nella subcartella /database del progetto e inseriscilo nella workbeanch, una volta fatto ciò avvia lo script e in questo modo avrai creato il database che ospiterà i dati del progetto

2. Aggiorna i dati di connessione al database (host, username, password) all'interno del file di configurazione Java del progetto.
   
---

## Step 2: Configurazione su Eclipse

Clona questo repository o scarica il codice sorgente.
Apri Eclipse e seleziona File > Import > Existing Projects into Workspace.
Associa il server Apache Tomcat al progetto tramite le proprietà del progetto (Targeted Runtimes).
Fai click con il tasto destro sul progetto -> Run As > Run on Server.
