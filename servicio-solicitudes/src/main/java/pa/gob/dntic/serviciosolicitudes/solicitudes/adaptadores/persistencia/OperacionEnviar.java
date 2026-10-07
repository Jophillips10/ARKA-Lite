package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.persistencia;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class OperacionEnviar {

    private final SolicitudJpaRepository solicitudes;
    private final EventoJpaRepository eventos;

    public OperacionEnviar(SolicitudJpaRepository solicitudes, EventoJpaRepository eventos) {
        this.solicitudes = solicitudes;
        this.eventos = eventos;
    }

    @Transactional
    public void enviar(String id) {
        SolicitudEntity s = solicitudes.findById(id).orElseThrow();
        s.setEstado("ENVIADA");
        solicitudes.save(s);
        eventos.save(new EventoEntity(id, "SolicitudEnviada"));
    }
}
