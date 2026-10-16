package pa.gob.dntic.servicionotificaciones.eventos;

public record SolicitudEnviada(String id, String tipo) {
    public String eventoId() {
        return id;
    }
}

