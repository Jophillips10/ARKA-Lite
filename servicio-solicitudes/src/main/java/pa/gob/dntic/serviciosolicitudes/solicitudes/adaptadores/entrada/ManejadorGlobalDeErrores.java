package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.entrada;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.SolicitudNoEncontrada;

@RestController
public class ManejadorGlobalDeErrores {

    @ExceptionHandler(SolicitudNoEncontrada.class)
    public ResponseEntity<ErrorRespuesta> noEncontrada(SolicitudNoEncontrada ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorRespuesta(404, ex.getMessage()));
    }
}
