package it.uniroma3.diadia.giocatore;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
    private Borsa borsa;
    private Attrezzo piombo;
    private Attrezzo piuma;

    @BeforeEach
    public void setUp() {
        borsa = new Borsa(10); // Borsa con peso massimo 10
        piombo = new Attrezzo("Piombo", 10);
        piuma = new Attrezzo("Piuma", 1);
    }

    @Test
    public void testAddAttrezzo_PesoConsentito() {
        assertTrue(borsa.addAttrezzo(piuma));
        assertEquals(1, borsa.getPeso());
    }

    @Test
    public void testAddAttrezzo_SuperaPesoMassimo() {
        borsa.addAttrezzo(piombo); // riempie la borsa
        assertFalse(borsa.addAttrezzo(piuma), "Non deve aggiungere se si supera il peso max");
    }

    @Test
    public void testGetAttrezzo_Presente() {
        borsa.addAttrezzo(piuma);
        assertEquals(piuma, borsa.getAttrezzo("Piuma"));
    }
}