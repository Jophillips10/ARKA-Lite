package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.salida;

import org.springframework.web.client.RestClient;
import pa.gob.dntic.serviciosolicitudes.eventos.SolicitudEnviada;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.Solicitud;
import java.util.UUID;


public class PublicadorConReintento {

    private final RestClient rest = RestClient.create();
    private final String url;

    public PublicadorConReintento(String url) {
        this.url = url;
    }

    public void publicar(String solicitudId, String tipo) {
        // eleventoId se genera UNA vez: si se reintenta, es el MISMO id -> el consumidor deduplica

        SolicitudEnviada evento = new SolicitudEnviada(UUID.randomUUID().toString(), solicitudId, tipo);
        for (int intento = 1; intento <= 3; intento++) {
            try {
                rest.post().uri(url).body(evento).retrieve().toBodilessEntity();
                return;
            } catch (RuntimeException ex) {
              if (intento == 3) {guardarParaReintento(evento);  return;}  // si falla 3 veces, lo guardo para reintento manual  }
            dormir(500L * intento);  // espera 1s, 2s, 3s antes de reintentar
            }
        }
    }


    private void guardarParaReintento(SolicitudEnviada e) {
        // en la vida real, a una tabla "outbox" que un job reenvia luego (mismo eventoId).
         System.err.println("no entregado tras 3 intentos; guardado para reintento: " + e.eventoId());
    }

    private void dormir(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
    }

}
