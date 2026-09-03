package pa.gob.dntic.arkalite.solicitudes.adaptadores.entrada;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pa.gob.dntic.arkalite.solicitudes.dominio.ServicioDeSolicitudes;
import pa.gob.dntic.arkalite.solicitudes.dominio.Solicitud;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

   private final ServicioDeSolicitudes servicio;

   public SolicitudController(ServicioDeSolicitudes servicio) {
       this.servicio = servicio;
   }

   @PostMapping
   public Solicitud guardar(@RequestBody CrearSolicitudRequest request) {
       return servicio.registrar(request.id(), request.tipo());
   }

   @GetMapping
   public List<Solicitud> todas() {
       return servicio.listar();
   }

   @GetMapping("/{id}")
   public Optional<Solicitud> porId(@PathVariable String id) {
        return servicio.buscar(id);
    }

   @PostMapping("/{id}/enviar")
   public Solicitud enviar(@PathVariable String id) {
       return servicio.enviar(id);
   }

   @PostMapping("/{id}/aprobar")
   public Solicitud aprobar(@PathVariable String id) {
       return servicio.aprobar(id);
   }

   @PostMapping("/{id}/rechazar")
   public Solicitud rechazar(@PathVariable String id) {
       return servicio.rechazar(id);
   }
}