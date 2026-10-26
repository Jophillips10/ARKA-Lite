package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.salida;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;
import pa.gob.dntic.serviciosolicitudes.eventos.SolicitudEnviada;

import java.util.UUID;

public class PublicadorConReintento {

    private static final Logger log = LoggerFactory.getLogger(PublicadorConReintento.class);

    private final RestClient rest;
    private final String url;

    public PublicadorConReintento(RestClient restInstrumentado, String url) {
        this.rest = restInstrumentado;
        this.url = url;
    }

    public void publicar(String solicitudId, String  tipo ) {
        SolicitudEnviada evento = new SolicitudEnviada(UUID.randomUUID().toString(), solicitudId, tipo);

        log.info("publica evento en {} de solicitud {}: {}", evento.eventoId(), solicitudId, tipo);
        for (int intento = 1; intento <= 3; intento++) {
            try {
                rest.post().uri(url).body(evento).retrieve().toBodilessEntity();
                return; // Salir si la publicación fue exitosa
            } catch (RuntimeException ex) {
                if (intento == 3) {
                    log.error("no entregado tras {} intentos; va a outbox: {}", intento, evento.eventoId(), ex);
                    guardarParaReintento(evento);
                    return;
                }
                log.warn("intento {} falló para {}; reintentando", intento, evento.eventoId());
                dormir(500L * intento);
            }
        }
    }


    private void guardarParaReintento(SolicitudEnviada e) {
        // Aquí se implementaría la lógica para guardar el evento en un outbox o base de datos
    }

    private void dormir(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

}
