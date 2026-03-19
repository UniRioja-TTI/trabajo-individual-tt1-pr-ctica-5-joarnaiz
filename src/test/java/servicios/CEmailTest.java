package servicios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CEmailTest {

    private CEmail cemail;
    @BeforeEach
    void setUp() {
        Logger l = Logger.getLogger(CEmailTest.class.getName());
        cemail = new CEmail(l);
    }

    @Test
    void enviarEmailTest(){
        boolean enviado = cemail.enviarEmail(null,"hola");
        assertTrue(enviado);
    }
}