package pa.gob.dntic.arkalite.solicitudes.adaptadores.salida;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pa.gob.dntic.arkalite.solicitudes.dominio.RepositorioDeSolicitudes;
import pa.gob.dntic.arkalite.solicitudes.dominio.Solicitud;

import java.util.*;

@Repository
@Primary
public class RepositorioEnMemoria implements RepositorioDeSolicitudes {

    //TODO: un map para guardar las solicitudes por id
    private final Map<String, Solicitud> almacen = new LinkedHashMap<>();

    public void guardar(Solicitud s){
        almacen.put(s.id(),s);
    }

    public Optional<Solicitud> buscar(String id){
        return Optional.ofNullable(almacen.get(id));
    }

    public List<Solicitud> todas(){
        return new ArrayList<>(almacen.values());
    }


}
