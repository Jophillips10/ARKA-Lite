package pa.gob.dntic.arkalite.notificaciones.dominio;

import pa.gob.dntic.arkalite.notificaciones.eventos.SolicitudEnviada;

public interface PublicadorDeEventos {
    void publicar(SolicitudEnviada evento);
}
