package pa.gob.dntic.arkalite.solicitudes.dominio;

import java.util.List;
import java.util.Optional;

public class ServicioDeSolicitudes {
    private final RepositorioDeSolicitudes repositorio;

    public ServicioDeSolicitudes(RepositorioDeSolicitudes repositorio) {
        this.repositorio = repositorio;
    }

    public Solicitud registrar(String id, String tipo) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id es obligatorio");
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo es obligatorio");
        }

        Solicitud nueva = new Solicitud(id, tipo, Estado.BORRADOR);
        repositorio.guardar(nueva);
        return nueva;
    }

    public Solicitud enviar(String id) {
        Solicitud solicitud = buscarEntidad(id);
        Solicitud enviada = solicitud.enviar();
        repositorio.guardar(enviada);
        return enviada;
    }

    public Solicitud aprobar(String id) {
        Solicitud solicitud = buscarEntidad(id);
        Solicitud aprobada = solicitud.aprobar();
        repositorio.guardar(aprobada);
        return aprobada;
    }

    public Solicitud rechazar(String id) {
        Solicitud solicitud = buscarEntidad(id);
        Solicitud rechazada = solicitud.rechazar();
        repositorio.guardar(rechazada);
        return rechazada;
    }

    public List<Solicitud> listar() {
        return repositorio.listar();
    }

    public Optional<Solicitud> buscar(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id es obligatorio");
        }
        return repositorio.buscar(id);
    }

    private Solicitud buscarEntidad(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id es obligatorio");
        }
        return repositorio.buscar(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe la solicitud: " + id));
    }
}
