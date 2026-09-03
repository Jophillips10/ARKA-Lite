package pa.gob.dntic.arkalite.solicitudes.adaptadores.entrada;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pa.gob.dntic.arkalite.solicitudes.dominio.ServicioDeSolicitudes;
import pa.gob.dntic.arkalite.solicitudes.dominio.Solicitud;

import java.util.List;

@RestController
public class SolicitudController {
    private final ServicioDeSolicitudes servicio;

    public SolicitudController(ServicioDeSolicitudes servicio){
        this.servicio = servicio;
    }

    @GetMapping("/solicitudes")
    public List<Solicitud> todas(){
        return  servicio.listar();
    }

    @GetMapping("/solicitudes/{id}")
    public Solicitud porId(@PathVariable String id){
        return servicio.buscar(id);
    }
    @PostMapping("/solicitudes/{id}/aprobar")
    public Solicitud aprobar(@PathVariable String id){return servicio.aprobar(id);}

    @PostMapping("/solicitudes/{id}/rechazar")
    public Solicitud rechazar(@PathVariable String id){return  servicio.rechazar(id);}

    @PostMapping("/solicitudes/{id}/enviar")
    public Solicitud enviar(@PathVariable String id){return servicio.enviar(id);}

    @PostMapping("/solicitudes/{id}/{tipo}/registrar")
    public Solicitud registrar(@PathVariable String id,@PathVariable String tipo)
    {return servicio.registrar(id,tipo);}
}
