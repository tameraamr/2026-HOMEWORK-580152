package it.uniroma3.diadia.giocatore;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GiocatoreTest {
    private Giocatore giocatore;

    @BeforeEach
    public void setUp() {
        giocatore = new Giocatore();
    }

    @Test
    public void testCfuIniziali() {
        assertEquals(20, giocatore.getCfu(), "I CFU iniziali devono essere 20");
    }

    @Test
    public void testSetCfu() {
        giocatore.setCfu(15);
        assertEquals(15, giocatore.getCfu());
    }
}