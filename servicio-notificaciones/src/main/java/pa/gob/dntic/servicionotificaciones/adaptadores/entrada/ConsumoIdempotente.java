package pa.gob.dntic.servicionotificaciones.adaptadores.entrada;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pa.gob.dntic.servicionotificaciones.eventos.SolicitudEnviada;
import pa.gob.dntic.servicionotificaciones.persistencia.EventoProcesadoEntity;
import pa.gob.dntic.servicionotificaciones.persistencia.EventoProcesadoRepository;
import pa.gob.dntic.servicionotificaciones.dominio.ServicioDeNotificaciones;

/*
*CONSUMO IDEMPOTENTE: Junta las dos ideas del módulo:
* - idempotencia (clase 25): si el eventoId ya fue procesado, no hace nada.
* - atomicidad (clase 24, @Transactional): crrear el aviso y marcar el evento
* como procesado ocurren juntos, o ninguno. Asi nunca queda "aviso sin marcar"
* (que se volveria a crear) ni "marcado sin aviso" (aviso perdido).
*/
@Service
public class ConsumoIdempotente {

    private final EventoProcesadoRepository procesados;
    private final ServicioDeNotificaciones servicio;

    public ConsumoIdempotente(EventoProcesadoRepository procesados, ServicioDeNotificaciones s) {
        this.procesados = procesados;
        this.servicio = s;
    }

    @Transactional
    public void alRecibir(SolicitudEnviada e) {
        if (procesados.existsById(e.eventoId())) {
            return; // Ya procesado, no hacer nada
        }

        servicio.alRecibirSolicitudEnviada(e);
        procesados.save(new EventoProcesadoEntity(e.eventoId()));
    }
}