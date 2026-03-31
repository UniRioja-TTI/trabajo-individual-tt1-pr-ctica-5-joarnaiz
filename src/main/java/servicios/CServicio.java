package servicios;

import interfaces.InterfazContactoSim;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;
import modelo.Punto;
import org.springframework.stereotype.Service;
import servicios.utilidades.ApiClient;
import servicios.utilidades.ApiResponse;
import servicios.utilidades.api.ResultadosApi;
import servicios.utilidades.api.SolicitudApi;
import servicios.utilidades.model.ResultsResponse;
import servicios.utilidades.model.Solicitud;
import servicios.utilidades.model.SolicitudResponse;

import java.util.*;

@Service
public class CServicio implements InterfazContactoSim {

    private final Map<Integer,DatosSolicitud> almacenDatos=new HashMap<>();
    private final Random rand=new Random();

    private ResultadosApi resultados;
    private SolicitudApi solicitud;
    public CServicio() {
        ApiClient client = new ApiClient();
        client.setBasePath("http://localhost:8080");
        this.resultados = new ResultadosApi(client);
        this.solicitud = new SolicitudApi(client);
    }

    @Override
    public int solicitarSimulation(DatosSolicitud sol) {
        try{
            Solicitud s = new Solicitud();
            ApiResponse<SolicitudResponse> tok = this.solicitud.solicitudSolicitarPostWithHttpInfo("alumno",s);
            int token = tok.getData().getTokenSolicitud();
            this.almacenDatos.put(token,sol);
            return token;
        }catch (Exception e) {
            System.out.println("Error al pedir token: " + e.getMessage());
            return -1;
        }

    }
    @Override
    public DatosSimulation descargarDatos(int ticket) {
        try {
            String usuario = "alumno";

            ApiResponse<ResultsResponse> respuesta = this.resultados.resultadosPostWithHttpInfo(usuario, ticket);
            ResultsResponse datos = respuesta.getData();
            String texto = datos.getData();

            System.out.println(texto); //Para ver el texto

            DatosSimulation simulacion = new DatosSimulation();

            String[] lineas = texto.split("\n");

            if (lineas.length > 0) {

                int tam = Integer.parseInt(lineas[0].trim());
                simulacion.setAnchoTablero(tam);
            }

            Map<Integer, List<Punto>> mapa = new HashMap<>();
            int tMax = 0;

            for (int i = 1; i < lineas.length; i++) {

                String[] partes = lineas[i].split(",");

                if (partes.length == 4) {
                    int t = Integer.parseInt(partes[0].trim());

                    Punto p = new Punto();
                    p.setY(Integer.parseInt(partes[1].trim()));
                    p.setX(Integer.parseInt(partes[2].trim()));
                    p.setColor(partes[3].trim());

                    if (t > tMax){
                        tMax = t;
                    }

                    if (!mapa.containsKey(t)) {
                        mapa.put(t, new ArrayList<>());
                    }
                    mapa.get(t).add(p);
                }
            }

            simulacion.setPuntos(mapa);
            simulacion.setMaxSegundos(tMax);

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
