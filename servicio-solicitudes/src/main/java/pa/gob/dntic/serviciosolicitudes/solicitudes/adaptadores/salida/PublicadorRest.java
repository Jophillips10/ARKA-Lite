package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.salida;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pa.gob.dntic.serviciosolicitudes.eventos.SolicitudEnviada;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.PublicadorDeEventos;

@Component
 public class PublicadorRest implements PublicadorDeEventos {
    private final RestClient rest = RestClient.create();
    private final String url;

    public PublicadorRest(@Value("${NOTIFICATION_URL}") String url) {
        this.url = url;
    }

    public void publicar(SolicitudEnviada evento) {
        // Aquí iría la lógica para publicar el evento a través de REST
        try {
            rest.post().uri(url).body(evento).retrieve().toBodilessEntity();
        } catch (RuntimeException e) {
            System.err.println("Error al publicar el evento: " + e.getMessage());
        }
    }
}