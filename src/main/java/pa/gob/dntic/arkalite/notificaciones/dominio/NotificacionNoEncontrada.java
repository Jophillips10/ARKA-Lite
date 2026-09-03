package pa.gob.dntic.arkalite.notificaciones.dominio;

public class NotificacionNoEncontrada extends RuntimeException {
    public NotificacionNoEncontrada(int id) {
        super("No existe la notificacion: " + id);
    }
}
