package pa.gob.dntic.servicionotificaciones.dominio;

public class NotificacionNoEncontrada extends RuntimeException {
    public NotificacionNoEncontrada(int id) {
        super("No existe la notificacion: " + id);
    }
}
