package pa.gob.dntic.arkalite.solicitudes.adaptadores.salida;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pa.gob.dntic.arkalite.solicitudes.dominio.RepositorioDeSolicitudes;
import pa.gob.dntic.arkalite.solicitudes.dominio.Solicitud;

import java.util.*;

@Repository
@Primary // Indica que esta implementación debe ser la principal cuando se inyecte RepositorioDeSolicitudes
public class RepositorioQueRegistra implements RepositorioDeSolicitudes {

    private final Map<String, Solicitud> almacen = new LinkedHashMap<>();

    public  void guardar(Solicitud s) {
        System.out.println("[repo] Guardando solicitud: " + s.id() + " - " + s.estado());
        almacen.put(s.id(), s);
    }

    public Optional <Solicitud> buscar(String id) {
        return Optional.ofNullable(almacen.get(id));
    }

    public List<Solicitud> listar() {
        return new ArrayList<>(almacen.values());
    }

public void rechazar(String id) {
        Solicitud solicitud = almacen.get(id);
        if (solicitud != null) {
            solicitud.rechazar();
            System.out.println("[repo] Solicitud rechazada: " + id);
        }
    }

    public void aprobar(String id) {
        Solicitud solicitud = almacen.get(id);
        if (solicitud != null) {
            solicitud.aprobar();
            System.out.println("[repo] Solicitud aprobada: " + id);
        }
    }


}
