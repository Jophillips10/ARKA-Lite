package pa.gob.dntic.servicionotificaciones.adaptadores.entrada;

import org.springframework.stereotype.Component;
import pa.gob.dntic.servicionotificaciones.eventos.SolicitudEnviada;

/* ADAPTADOR de entrada de Notificaciones: recibe el evento y llama a su servicio. */
@Component
public class ManejadorDeEventos {
    private final ConsumoIdempotente consumo;

    public ManejadorDeEventos(ConsumoIdempotente consumo) {
        this.consumo = consumo;
    }

    public void manejar(SolicitudEnviada evento) {
        consumo.alRecibir(evento);
    }
}
