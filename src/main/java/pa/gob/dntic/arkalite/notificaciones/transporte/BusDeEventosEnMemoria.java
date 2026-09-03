package pa.gob.dntic.arkalite.notificaciones.transporte;

import org.springframework.stereotype.Component;
import pa.gob.dntic.arkalite.notificaciones.dominio.PublicadorDeEventos;
import pa.gob.dntic.arkalite.notificaciones.eventos.SolicitudEnviada;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class BusDeEventosEnMemoria implements PublicadorDeEventos {
    private final List<Consumer<SolicitudEnviada>> suscriptores = new ArrayList<>();

    public void suscribir(Consumer<SolicitudEnviada> manejador){
        suscriptores.add(manejador);

    }

    public void publicar (SolicitudEnviada evento){
        for(Consumer<SolicitudEnviada> s: suscriptores){
            try {
                s.accept(evento);
            }catch (RuntimeException fallo)
            {
                System.err.println(fallo.getMessage());
            }
        }
    }
}
