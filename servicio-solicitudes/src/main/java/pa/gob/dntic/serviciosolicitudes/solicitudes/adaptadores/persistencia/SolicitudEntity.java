package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.persistencia;

import jakarta.persistence.*;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.Solicitud;

@Entity
@Table(name = "solicitud")
public class SolicitudEntity {
    @Id
    private String id;
    private String tipo;
    private String estado;

    protected SolicitudEntity() {}
    public SolicitudEntity(String id, String tipo, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getId() { return id;}
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) {this.estado = estado; }
}
