package pa.gob.dntic.arkalite.notificaciones.dominio;

import pa.gob.dntic.arkalite.solicitudes.dominio.Solicitud;

import java.util.List;

public interface RepositorioDeNotificaciones {
    void publicar(Notificacion texto);
    List<Notificacion> todas();
}
