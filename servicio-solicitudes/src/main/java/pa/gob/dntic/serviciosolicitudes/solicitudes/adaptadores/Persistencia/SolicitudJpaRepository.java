package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.Persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudJpaRepository extends JpaRepository<SolicitudEntity, String> {
    List<SolicitudEntity> findByEstado(String estado);
}
