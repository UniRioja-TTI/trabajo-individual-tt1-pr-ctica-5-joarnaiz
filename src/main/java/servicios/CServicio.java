package servicios;

import interfaces.InterfazContactoSim;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CServicio implements InterfazContactoSim {

    private final Map<Integer,DatosSolicitud> almacenDatos=new HashMap<>();
    private final Random rand=new Random();

    @Override
    public int solicitarSimulation(DatosSolicitud sol) {
        int token = rand.nextInt(100);
        this.almacenDatos.put(token,sol);
        return token;
    }
    @Override
    public DatosSimulation descargarDatos(int ticket) {
        return null;
    }

    @Override
    public List<Entidad> getEntities() {
        Entidad e1=new Entidad();
        Entidad e2=new Entidad();
        e1.setId(1);
        e2.setId(2);
        e1.setName("J");
        e2.setName("A");
        return List.of(e1,e2);
    }

    @Override
    public boolean isValidEntityId() {
        return true;
    }
}
