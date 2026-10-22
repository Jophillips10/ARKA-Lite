package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.salida;

import org.springframework.web.client.RestClient;
import pa.gob.dntic.serviciosolicitudes.eventos.SolicitudEnviada;
import java.util.UUID;

public class PublicadorConReintento {

    private final String url;

        this.url = url;
    }

    public void publicar(String solicitudId, String  tipo ) {
        SolicitudEnviada evento = new SolicitudEnviada(UUID.randomUUID().toString(), solicitudId, tipo);
        for (int intento = 1; intento <= 3; intento++) {
            try {
                rest.post().uri(url).body(evento).retrieve().toBodilessEntity();
                    return;
            }
        }
    }


    private void guardarParaReintento(SolicitudEnviada e) {
           }

    private void dormir(long ms) {
    }

}
