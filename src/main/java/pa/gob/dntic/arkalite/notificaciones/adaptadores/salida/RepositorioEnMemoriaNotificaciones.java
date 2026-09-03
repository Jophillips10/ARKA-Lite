package pa.gob.dntic.arkalite.notificaciones.adaptadores.salida;

import org.springframework.stereotype.Repository;
import pa.gob.dntic.arkalite.notificaciones.dominio.Notificacion;
import pa.gob.dntic.arkalite.notificaciones.dominio.RepositorioDeNotificaciones;
import pa.gob.dntic.arkalite.solicitudes.dominio.Solicitud;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioEnMemoriaNotificaciones implements RepositorioDeNotificaciones {
    private final List<Notificacion> colaNotificaciones = new ArrayList<>();

    public void publicar(Notificacion notificacion){
        colaNotificaciones.add(notificacion);
    }

    public List<Notificacion> todas(){
        return colaNotificaciones.stream().toList();
    }


}
