package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * @author  docente di POO 
 * @version base
 */
public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole io; // Centralizziamo I/O

	// Il costruttore ora richiede IOConsole come parametro
	public DiaDia(IOConsole console) {
		this.io = console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
				
		do {		
			istruzione = this.io.leggiRiga();
		} while (!processaIstruzione(istruzione));
	}   

	/**
	 * Processa una istruzione 
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if (istruzione == null) return false;
		
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome() == null) {
			this.io.mostraMessaggio("Comando vuoto");
		} else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("aiuto")) {
			this.aiuto();
		} else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		} else {
			this.io.mostraMessaggio("Comando sconosciuto");
		}
		
		if (this.partita.vinta()) {
			this.io.mostraMessaggio("Hai vinto!");
			return true;
		} else {
			return false;
		}
	}   

	// --- IMPLEMENTAZIONE DEI COMANDI DELL'UTENTE ---

	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.io.mostraMessaggio("Cosa vuoi prendere? Specifica un attrezzo.");
			return;
		}
		
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Attrezzo attrezzoCercato = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		
		if (attrezzoCercato == null) {
			this.io.mostraMessaggio("Attrezzo non presente nella stanza.");
		} else {
			Giocatore giocatore = this.partita.getGiocatore();
			if (giocatore.getBorsa().addAttrezzo(attrezzoCercato)) {
				stanzaCorrente.removeAttrezzo(attrezzoCercato);
				this.io.mostraMessaggio("Hai preso: " + attrezzoCercato.getNome());
			} else {
				this.io.mostraMessaggio("Non puoi prendere l'attrezzo. Borsa piena o attrezzo troppo pesante!");
			}
		}
	}

	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.io.mostraMessaggio("Cosa vuoi posare? Specifica un attrezzo.");
			return;
		}
		
		Giocatore giocatore = this.partita.getGiocatore();
		Attrezzo attrezzoCercato = giocatore.getBorsa().getAttrezzo(nomeAttrezzo);
		
		if (attrezzoCercato == null) {
			this.io.mostraMessaggio("Non hai questo attrezzo nella borsa.");
		} else {
			Stanza stanzaCorrente = this.partita.getStanzaCorrente();
			if (stanzaCorrente.addAttrezzo(attrezzoCercato)) {
				giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
				this.io.mostraMessaggio("Hai posato: " + attrezzoCercato.getNome());
			} else {
				this.io.mostraMessaggio("Non c'è spazio in questa stanza per posare l'attrezzo.");
			}
		}
	}

	private void aiuto() {
		// Accumuliamo i comandi in una stringa e stampiamo una volta sola
		StringBuilder info = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) {
			info.append(elencoComandi[i]).append(" ");
		}
		this.io.mostraMessaggio(info.toString());
	}

	private void vai(String direzione) {
		if(direzione==null)
			this.io.mostraMessaggio("Dove vuoi andare ?");
		
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null) {
			this.io.mostraMessaggio("Direzione inesistente");
		} else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu - 1); 
		}
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.io.mostraMessaggio("Borsa: " + partita.getGiocatore().getBorsa().getPeso() + "kg/" + partita.getGiocatore().getBorsa().getPesoMax() + "kg");
	}

	private void fine() {
		this.io.mostraMessaggio("Grazie di aver giocato!");
	}

	public static void main(String[] argc) {
		// UNICA creazione di IOConsole come richiesto
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}