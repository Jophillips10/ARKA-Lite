package pa.gob.dntic.arkalite.notificaciones.adaptadores.salida;

import org.springframework.stereotype.Repository;
import pa.gob.dntic.arkalite.notificaciones.dominio.Notificacion;
import pa.gob.dntic.arkalite.notificaciones.dominio.RepositorioDeNotificaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("repositorioDeNotificacionesEnMemoria")
public class RepositorioEnMemoria implements RepositorioDeNotificaciones {

    private final Map<String, Notificacion> almacen = new HashMap<>();
//private final List<Notificacion> almacen1 = ArrayList<>();

    @Override
    public void guardar(Notificacion notificacion) {
        almacen.put(notificacion.texto(), notificacion);
    }

    @Override
    public Optional<Notificacion> buscar(String texto) {
        return Optional.ofNullable(almacen.get(texto));
    }

    @Override
    public List<Notificacion> listar() {
        return new ArrayList<>(almacen.values());
    }
}
