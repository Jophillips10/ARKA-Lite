package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.salida;

import org.springframework.stereotype.Repository;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.RepositorioDeSolicitudes;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.Solicitud;

import java.util.List;
import java.util.Optional;

@Repository("repositorioDeSolicitudesEnMemoria")
public class RepositorioEnMemoria implements RepositorioDeSolicitudes {

    // TODO: un Map para guardar las solicitudes por id


    public void guardar(Solicitud s) {
        //TODO
        throw new UnsupportedOperationException("TODO: guardar");
    }

    public Optional<Solicitud> buscar(String id) {
        // TODO
        throw new UnsupportedOperationException("TODO: buscar()");
    }

    public List<Solicitud> todas() {
        // TODO
        throw new UnsupportedOperationException("TODO: todas()");
    }
}
