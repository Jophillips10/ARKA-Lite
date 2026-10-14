package pa.gob.dntic.servicionotificaciones.adaptadores.persistencia;

// La "memoria" de eventos ya procesados, en la base de NOTIFICACIONES (no compartida).
@Entity
@Table(name = "evento_procesado")
public class EventoProcesadoEntity {
    @Id private String eventoId;
    protected EventoProcesadoEntity() {}

    public EventoProcesadoEntity(String eventoId) {
        this.eventoId = eventoId;
    }
}
