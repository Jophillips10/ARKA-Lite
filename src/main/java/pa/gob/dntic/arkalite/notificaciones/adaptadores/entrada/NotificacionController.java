package pa.gob.dntic.arkalite.notificaciones.adaptadores.entrada;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pa.gob.dntic.arkalite.notificaciones.dominio.Notificacion;
import pa.gob.dntic.arkalite.notificaciones.dominio.ServicioDeNotificaciones;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final ServicioDeNotificaciones servicio;

    public NotificacionController(ServicioDeNotificaciones servicio) {
        this.servicio = servicio;
    }

    @PostMapping
//    public Notificacion guardar(@RequestBody CrearNotificacionRequest request) {
//        return servicio.guardar(request.texto());
//    }

    @GetMapping
    public List<Notificacion> listar() {
        return servicio.listar();
    }

//    @GetMapping("/{texto}")
//    public Optional<Notificacion> buscar(@PathVariable String texto) {
//        return servicio.buscar(texto);
//    }

}