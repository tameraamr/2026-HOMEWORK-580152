package it.uniroma3.diadia;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

    private Partita partita;

    @BeforeEach
    public void setUp() {
        // Genera un'istanza pulita di Partita prima di ogni test
        partita = new Partita();
    }

    // --- TEST PER IL METODO vinta() ---

    @Test
    public void testVinta_InizioPartita() {
        assertFalse(partita.vinta(), "Appena creata, la partita non deve risultare vinta");
    }

    @Test
    public void testVinta_RaggiuntaStanzaVincente() {
        // MODIFICA: Ora chiediamo la stanza vincente al Labirinto della partita
        Stanza vincente = partita.getLabirinto().getStanzaVincente(); 
        partita.setStanzaCorrente(vincente);
        assertTrue(partita.vinta(), "Se il giocatore si trova nella stanza vincente, la partita è vinta");
    }

    @Test
    public void testVinta_StanzaQualsiasiNonVincente() {
        Stanza nonVincente = new Stanza("Stanza a caso");
        partita.setStanzaCorrente(nonVincente);
        assertFalse(partita.vinta(), "Trovarsi in una stanza diversa da quella vincente non fa vincere la partita");
    }

    // --- TEST PER IL METODO isFinita() ---

    @Test
    public void testIsFinita_CfuEsauriti() {
        partita.setCfu(0);
        assertTrue(partita.isFinita(), "La partita deve essere considerata finita se i CFU scendono a 0");
    }

    @Test
    public void testIsFinita_ImpostataManualmente() {
        partita.setFinita();
        assertTrue(partita.isFinita(), "La partita deve finire se richiamato esplicitamente setFinita()");
    }

    @Test
    public void testIsFinita_VittoriaRaggiunta() {
        // MODIFICA: Ora chiediamo la stanza vincente al Labirinto della partita
        partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente()); 
        assertTrue(partita.isFinita(), "Se la partita è vinta, deve risultare anche finita");
    }
}