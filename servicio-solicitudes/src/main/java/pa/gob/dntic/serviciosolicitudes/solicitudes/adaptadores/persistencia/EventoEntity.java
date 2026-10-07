package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.persistencia;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "evento")
public class EventoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "solicitud_id")
    private String solicitudId;
    private String tipo;

    protected EventoEntity() {}
    public EventoEntity(String solicitudId, String tipo) {
        this.solicitudId = solicitudId;
        this.tipo = tipo;}
    public Long getId() { return id; }
}
