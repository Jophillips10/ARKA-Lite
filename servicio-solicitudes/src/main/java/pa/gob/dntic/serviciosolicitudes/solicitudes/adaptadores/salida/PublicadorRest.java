package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.salida;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pa.gob.dntic.serviciosolicitudes.eventos.SolicitudEnviada;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.PublicadorDeEventos;

@Component
public class PublicadorRest implements PublicadorDeEventos {
   private static final int MAX_INTENTOS = 5;
   private final RestClient rest = RestClient.create();
   private final String url;

   public PublicadorRest(@Value("${notificaciones.url}") String url) {
       this.url = url;
   }

   public void publicar(SolicitudEnviada evento) {
       for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
           try {
               rest.post()
                   .uri(url)
                   .contentType(MediaType.APPLICATION_JSON)
                   .body(evento)
                   .retrieve()
                   .toBodilessEntity();
               return;
           } catch (RuntimeException e) {
               if (intento == MAX_INTENTOS) {
                   System.err.println("Error al publicar el evento tras " + MAX_INTENTOS + " intentos: " + e.getMessage());
                   return;
               }
               System.err.println("Reintentando publicar el evento (" + intento + "/" + MAX_INTENTOS + "): " + e.getMessage());
               try {
                   Thread.sleep(2000L * intento);
               } catch (InterruptedException ie) {
                   Thread.currentThread().interrupt();
                   return;
               }
           }
       }
   }
}