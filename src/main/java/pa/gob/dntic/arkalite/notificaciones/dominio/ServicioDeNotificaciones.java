package pa.gob.dntic.arkalite.notificaciones.dominio;

import pa.gob.dntic.arkalite.solicitudes.dominio.Estado;
import pa.gob.dntic.arkalite.solicitudes.dominio.Solicitud;

import java.util.List;

public class ServicioDeNotificaciones {
    private final RepositorioDeNotificaciones repositorio;

    public ServicioDeNotificaciones(RepositorioDeNotificaciones repositorio){
        this.repositorio = repositorio;
    }

    public Notificacion publicar(String texto){
        // TODO: crear una solicitud en Borrador, guardarla en el repositorio y devolverla
        Notificacion nuevaNotificacion = new Notificacion(texto);
        repositorio.publicar(nuevaNotificacion);
        return nuevaNotificacion;

    }

    public List<Notificacion> listar(){
        // TODO: devolver todas las del repositorio
        return repositorio.todas();

    }
}
