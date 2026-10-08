package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.Persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitud")
public class SolicitudEntity {

    @Id
    private String id;
    private String tipo;
    private String estado;

    // Constructor requerido por JPA/Hibernate
    protected SolicitudEntity() {  }

    // Constructor para crear solicitudes desde tu aplicación
    protected SolicitudEntity(String id, String tipo, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
