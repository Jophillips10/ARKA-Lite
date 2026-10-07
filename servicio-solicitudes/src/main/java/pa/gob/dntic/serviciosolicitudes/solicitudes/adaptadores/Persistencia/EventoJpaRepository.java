package pa.gob.dntic.serviciosolicitudes.solicitudes.adaptadores.Persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoJpaRepository extends JpaRepository<EventoEntity, Long> {
}
