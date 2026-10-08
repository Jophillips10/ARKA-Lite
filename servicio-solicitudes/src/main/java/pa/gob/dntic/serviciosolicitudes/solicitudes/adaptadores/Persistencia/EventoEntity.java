package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.Persistencia;

import jakarta.persistence.*;

@Entity
@Table(name = "evento")
public class EventoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "solicitud_id")
    private String solicitudId;
    private  String tipo;

    protected EventoEntity () {}
    public EventoEntity(String solicitudId, String tipo) {
        this.solicitudId = solicitudId;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

}
