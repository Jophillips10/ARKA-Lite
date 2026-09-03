package pa.gob.dntic.arkalite.solicitudes.adaptadores.salida;

import org.springframework.stereotype.Repository;
import pa.gob.dntic.arkalite.solicitudes.dominio.RepositorioDeSolicitudes;
import pa.gob.dntic.arkalite.solicitudes.dominio.Solicitud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RepositorioEnMemoria implements RepositorioDeSolicitudes {

    private final Map<String, Solicitud> almacen = new HashMap<>();

    @Override
    public void guardar(Solicitud s) {
        almacen.put(s.id(), s);
    }

    @Override
    public Optional<Solicitud> buscar(String id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public List<Solicitud> listar() {
        return new ArrayList<>(almacen.values());
    }

    @Override
    public void rechazar(String id) {
        Solicitud solicitud = almacen.get(id);
        if (solicitud != null) {
            almacen.put(id, solicitud.rechazar());
        }
    }

    @Override
    public void aprobar(String id) {
        Solicitud solicitud = almacen.get(id);
        if (solicitud != null) {
            almacen.put(id, solicitud.aprobar());
        }
    }
}
