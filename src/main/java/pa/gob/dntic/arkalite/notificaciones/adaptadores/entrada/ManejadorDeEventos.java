package pa.gob.dntic.arkalite.notificaciones.adaptadores.entrada;

import org.springframework.stereotype.Component;
import pa.gob.dntic.arkalite.notificaciones.dominio.ServicioDeNotificaciones;
import pa.gob.dntic.arkalite.notificaciones.eventos.SolicitudEnviada;

@Component
public class ManejadorDeEventos {
    private final ServicioDeNotificaciones servicio;

    public ManejadorDeEventos(ServicioDeNotificaciones servicio){
        this.servicio = servicio;
    }

    public void manejar (SolicitudEnviada evento){
        servicio.alRecibirSolicitudEnviada(evento);
    }
}
