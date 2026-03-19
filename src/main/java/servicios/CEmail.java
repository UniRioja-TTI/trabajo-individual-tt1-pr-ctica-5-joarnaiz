package servicios;

import interfaces.InterfazEnviarEmails;
import modelo.Destinatario;

import java.util.logging.Logger;

public class CEmail implements InterfazEnviarEmails {

    private Logger log;
    public CEmail(Logger logger) {
        this.log = logger;
    }

    @Override
    public boolean enviarEmail(Destinatario dest, String email) {
        this.log.info("Enviando email a: "+dest+ "contenido: "+email);
        return true;
    }
}
