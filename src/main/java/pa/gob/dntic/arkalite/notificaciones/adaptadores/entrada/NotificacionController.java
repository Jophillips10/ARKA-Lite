package pa.gob.dntic.arkalite.notificaciones.adaptadores.entrada;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pa.gob.dntic.arkalite.notificaciones.dominio.Notificacion;
import pa.gob.dntic.arkalite.notificaciones.dominio.ServicioDeNotificaciones;
import pa.gob.dntic.arkalite.solicitudes.dominio.Solicitud;

import java.util.List;

@RestController
public class NotificacionController {
    private final ServicioDeNotificaciones servicio;
    public NotificacionController (ServicioDeNotificaciones servicio){
        this.servicio = servicio;
    }

    @PostMapping("/publicar/{textoNotificacion}")
    public Notificacion publicarNotificacion(@PathVariable String textoNotificacion){
        return servicio.publicar(textoNotificacion);
    }

    @GetMapping("/listar")
    public List<Notificacion> todas(){
        return  servicio.listar();
    }


}
