package servicios;

import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CServicioTest {

    private CServicio servicio;
    @BeforeEach
    void setUp() {
        servicio = new CServicio();
    }

    @Test
    void solicitarSimulation() {
        DatosSolicitud datosSolicitud=new DatosSolicitud(new HashMap<>());
        int resultado = servicio.solicitarSimulation(datosSolicitud);
        assertTrue(resultado>=0 && resultado<=100);
    }

    @Test
    void descargarDatos() {
        DatosSimulation resultado = servicio.descargarDatos(1);
        assertNotNull(resultado,"No debe ser null por defecto");
    }

    @Test
    void getEntities() {
        List<Entidad> lista = servicio.getEntities();
        assertNotNull(lista,"Debe ser null por defecto");
    }

    @Test
    void isValidEntityId() {
        boolean valido = servicio.isValidEntityId(1);
        assertTrue(valido,"Debe ser verdadero");
        assertFalse(servicio.isValidEntityId(-1),"Debería ser falso");
    }
}