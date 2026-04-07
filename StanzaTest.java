package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

    private Stanza stanzaVuota;
    private Stanza stanzaAdiacente;
    private Attrezzo spada;

    @BeforeEach
    public void setUp() {
        // Stato iniziale semplice e isolato per ogni test
        stanzaVuota = new Stanza("Stanza Vuota");
        stanzaAdiacente = new Stanza("Stanza Adiacente");
        spada = new Attrezzo("Spada", 5); // Assumendo costruttore (nome, peso) come visto in Partita
    }

    // --- TEST PER LA GESTIONE DEGLI ATTREZZI ---

    @Test
    public void testAddAttrezzo_InserimentoCorretto() {
        assertTrue(stanzaVuota.addAttrezzo(spada), "L'attrezzo dovrebbe essere aggiunto con successo");
        assertTrue(stanzaVuota.hasAttrezzo("Spada"), "La stanza dovrebbe ora contenere l'attrezzo");
    }

    @Test
    public void testAddAttrezzo_LimiteMassimo() {
        // Riempiamo la stanza fino al limite massimo (10)
        for (int i = 0; i < 10; i++) {
            stanzaVuota.addAttrezzo(new Attrezzo("Oggetto" + i, 1));
        }
        assertFalse(stanzaVuota.addAttrezzo(spada), "Non dovrebbe essere possibile aggiungere l'11esimo attrezzo");
    }

    @Test
    public void testHasAttrezzo_NonPresente() {
        stanzaVuota.addAttrezzo(spada);
        assertFalse(stanzaVuota.hasAttrezzo("Scudo"), "La ricerca di un attrezzo inesistente deve restituire false");
    }

    // --- TEST PER LA GESTIONE DELLE ADIACENZE ---

    @Test
    public void testImpostaStanzaAdiacente_RecuperoCorretto() {
        stanzaVuota.impostaStanzaAdiacente("nord", stanzaAdiacente);
        assertEquals(stanzaAdiacente, stanzaVuota.getStanzaAdiacente("nord"), "Dovrebbe restituire la stanza appena impostata a nord");
    }

    @Test
    public void testImpostaStanzaAdiacente_SovrascritturaDirezione() {
        Stanza nuovaStanzaNord = new Stanza("Nuova Nord");
        stanzaVuota.impostaStanzaAdiacente("nord", stanzaAdiacente);
        stanzaVuota.impostaStanzaAdiacente("nord", nuovaStanzaNord); // Sovrascrive
        
        assertEquals(nuovaStanzaNord, stanzaVuota.getStanzaAdiacente("nord"), "La stanza a nord dovrebbe essere aggiornata");
    }

    @Test
    public void testGetStanzaAdiacente_DirezioneInesistente() {
        stanzaVuota.impostaStanzaAdiacente("sud", stanzaAdiacente);
        assertNull(stanzaVuota.getStanzaAdiacente("nord"), "Cercare in una direzione vuota deve restituire null");
    }
}