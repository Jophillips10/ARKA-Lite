package pa.gob.dntic.arkalite.notificaciones.dominio;

import java.util.List;
import java.util.Optional;

public class ServicioDeNotificaciones {
    private final RepositorioDeNotificaciones repositorio;

    public ServicioDeNotificaciones(RepositorioDeNotificaciones repositorio) {
        this.repositorio = repositorio;
    }

    public Notificacion guardar(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El texto es obligatorio");
        }

        Notificacion nueva = new Notificacion(texto);
        repositorio.guardar(nueva);
        return nueva;
    }

    public Optional<Notificacion> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El texto es obligatorio");
        }
        return repositorio.buscar(texto);
    }

    public List<Notificacion> listar() {
        return repositorio.listar();
    }
}
