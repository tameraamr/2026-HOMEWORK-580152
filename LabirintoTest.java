package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LabirintoTest {
    private Labirinto labirinto;

    @BeforeEach
    public void setUp() {
        labirinto = new Labirinto();
    }

    @Test
    public void testStanzaInizialeEsistente() {
        assertNotNull(labirinto.getStanzaIniziale(), "Il labirinto deve avere una stanza iniziale");
        assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
    }

    @Test
    public void testStanzaVincenteEsistente() {
        assertNotNull(labirinto.getStanzaVincente(), "Il labirinto deve avere una stanza vincente");
        assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
    }
}