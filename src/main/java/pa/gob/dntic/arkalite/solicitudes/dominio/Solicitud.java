package pa.gob.dntic.arkalite.solicitudes.dominio;

public record Solicitud(String id, String tipo, Estado estado) {

    public Solicitud enviar() {
        if (estado != Estado.BORRADOR) {
            throw new IllegalStateException("Solo se puede enviar una solicitud en BORRADOR");
        }
        return new Solicitud(id, tipo, Estado.ENVIADA);
    }

    public Solicitud aprobar() {
        if (estado != Estado.ENVIADA) {
            throw new IllegalStateException("Solo se aprueba una solicitud ENVIADA");
        }
        return new Solicitud(id, tipo, Estado.APROBADA);
    }

    public Solicitud rechazar() {
        if (estado != Estado.ENVIADA) {
            throw new IllegalStateException("Solo se rechaza una solicitud ENVIADA");
        }
        return new Solicitud(id, tipo, Estado.RECHAZADA);
    }

}
