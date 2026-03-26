package servicios;

import interfaces.InterfazContactoSim;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;
import org.springframework.stereotype.Service;
import servicios.utilidades.ApiClient;
import servicios.utilidades.ApiResponse;
import servicios.utilidades.api.ResultadosApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CServicio implements InterfazContactoSim {

    private final Map<Integer,DatosSolicitud> almacenDatos=new HashMap<>();
    private final Random rand=new Random();

    private ResultadosApi resultados;
    public CServicio() {
        ApiClient client = new ApiClient();
        client.setBasePath("http://localhost:8080/");
        this.resultados = new ResultadosApi(client);
    }

    @Override
    public int solicitarSimulation(DatosSolicitud sol) {
        int token = rand.nextInt(100);
        this.almacenDatos.put(token,sol);
        return token;
    }
    @Override
    public DatosSimulation descargarDatos(int ticket) {
        try {
            String usuario = "alumno";

            ApiResponse respuesta = this.resultados.resultadosPostWithHttpInfo(usuario, ticket);

            String texto = (String) respuesta.getData();

            DatosSimulation simulacion = new DatosSimulation();

            String[] lineas = texto.split("\n");

            if (lineas.length > 0) {
                int tamaño = Integer.parseInt(lineas[0].trim());
            }

            for (int i = 1; i < lineas.length; i++) {
                String lineaColor = lineas[i].trim();
                if (!lineaColor.isEmpty()) {
                    String[] partes = lineaColor.split(",");

                }
            }

            return simulacion;

        } catch (Exception e) {
            System.out.println("Error al descargar los datos : " + e.getMessage());
            return new DatosSimulation();
        }
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
    public boolean isValidEntityId(int id) {
        return id>=0;
    }
}
