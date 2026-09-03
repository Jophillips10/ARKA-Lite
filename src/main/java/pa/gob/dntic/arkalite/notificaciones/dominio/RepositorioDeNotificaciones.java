package pa.gob.dntic.arkalite.notificaciones.dominio;

import java.util.List;
import java.util.Optional;

public interface RepositorioDeNotificaciones {
   void guardar(Notificacion notificacion);
   Optional<Notificacion> buscar(String texto);
   List<Notificacion> listar();
}
