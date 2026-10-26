package pa.gob.dntic.servicionotificaciones.adaptadores.entrada;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pa.gob.dntic.servicionotificaciones.adaptadores.persistencia.EventoProcesadoEntity;
import pa.gob.dntic.servicionotificaciones.adaptadores.persistencia.EventoProcesadoRepository;
import pa.gob.dntic.servicionotificaciones.dominio.ServicioDeNotificaciones;
import pa.gob.dntic.servicionotificaciones.eventos.SolicitudEnviada;

/*
*CONSUMO IDEMPOTENTE: Junta las dos ideas del módulo:
* - idempotencia (clase 25): si el eventoId ya fue procesado, no hace nada.
* - atomicidad (clase 24, @Transactional): crrear el aviso y marcar el evento
* como procesado ocurren juntos, o ninguno. Asi nunca queda "aviso sin marcar"
* (que se volveria a crear) ni "marcado sin aviso" (aviso perdido).
*/
@Service
public class ConsumoIdempotente {

    private  static final Logger log = LoggerFactory.getLogger(ConsumoIdempotente.class);

    private final EventoProcesadoRepository procesados;
    private final ServicioDeNotificaciones servicio;

    public ConsumoIdempotente(EventoProcesadoRepository p, ServicioDeNotificaciones s) {
        this.procesados = p;
        this.servicio = s;
    }

    @Transactional
    public void alRecibir(SolicitudEnviada e) {
        log.info("consumo evento {} de solicitud {}", e.eventoId(), e.id());
        if (procesados.existsById(e.id())) {
            log.info("evento {} ya procesado; se ignora espacio (idempotente)", e.eventoId());
            return; // Ya procesado, no hacer nada
        }

        try {
            servicio.alRecibirSolicitudEnviada(e);
            procesados.save(new EventoProcesadoEntity(e.eventoId()));
            log.info("aviso creado para solicitud {} (evento {})", e.id(), e.eventoId());
        } catch (RuntimeException ex) {
            log.error("falló al guardar el aviso de {} (evento {})", e.id(), e.eventoId(), ex);
            throw ex;
        }

//        servicio.alRecibirSolicitudEnviada(e);
//        procesados.save(new EventoProcesadoEntity(e.id()));
    }

//    private java.lang.Object e() {
//    }
}