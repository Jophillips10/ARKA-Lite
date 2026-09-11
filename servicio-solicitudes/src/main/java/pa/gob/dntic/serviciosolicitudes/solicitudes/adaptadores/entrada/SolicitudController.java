package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.entrada;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.Estado;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.ServicioDeSolicitudes;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.Solicitud;

import java.util.List;


@RestController
public class SolicitudController {

    private final ServicioDeSolicitudes servicio;

    public SolicitudController(ServicioDeSolicitudes servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/solicitudes")
    public List<Solicitud> todas() {
        return servicio.listar();
    }

    @GetMapping("/solicitudes/{id}")
    public Solicitud porId(@PathVariable String id) {
        return servicio.buscar(id);
    }

    @PostMapping("solicitudes/crear")
    public Solicitud crearNueva() {
        Solicitud nueva = new Solicitud ("INC-002", "Incidencia", Estado.BORRADOR);
        return servicio.registrar(nueva.id(), nueva.tipo());
    }

    @PostMapping("/solicitudes/{id}/enviar")
    public Solicitud enviar(@PathVariable String id) {
        return servicio.enviar(id);
    }
}