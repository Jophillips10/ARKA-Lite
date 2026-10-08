package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.Persistencia;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * "Enviar" toca DOS tablas: actualiza solicitud y registra un evento.
 * @Transactional hace que las dos escrituras sean ATOMICAS: o ambas, o ninguna.
 * Si algo falla dentro del metodo, spring hace ROLLBACK de todo automaticamente.
 */

@Service
public class OperacionEnviar {

    private final SolicitudJpaRepository solicitudes;
    private final EventoJpaRepository eventos;

    public OperacionEnviar(SolicitudJpaRepository s, EventoJpaRepository e) {
        this.solicitudes = s;
        this.eventos = e;
    }

    @Transactional
    public void enviar(String id) {
        SolicitudEntity s = solicitudes.findById(id).orElseThrow();
        s.setEstado("ENVIADA"); // escritura 1: update solicitud
        solicitudes.save(s);
        //EventoEntity evento = new EventoEntity(solicitudId, "ENVIADA");
        eventos.save(new EventoEntity(id, "Solicitud Enviada")); // escritura 2: insert evento
        //si cualquier error ocurre, la transacción se revierte y no se guarda nada
    }

}
