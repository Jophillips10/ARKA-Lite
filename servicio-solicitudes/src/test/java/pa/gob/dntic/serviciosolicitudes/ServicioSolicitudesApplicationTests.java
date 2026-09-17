package pa.gob.dntic.serviciosolicitudes;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.Estado;
import pa.gob.dntic.serviciosolicitudes.solicitudes.dominio.Solicitud;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicioSolicitudesApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void deberiaEnviarSolicitudEnBorrador() {

        Solicitud solicitud =
                new Solicitud("INC-002", "Incidencia", Estado.BORRADOR);

        Solicitud resultado = solicitud.enviar();

        assertEquals("INC-002", resultado.id());
        assertEquals("Incidencia", resultado.tipo());
        assertEquals(Estado.ENVIADA, resultado.estado());
    }

    @Test
    void noDeberiaEnviarSolicitudSiNoEstaEnBorrador() {

        Solicitud solicitud =
                new Solicitud("INC-002", "Incidencia", Estado.ENVIADA);

        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                solicitud::enviar
        );

        assertEquals(
                "solo se puede enviar una solicitud en BORRADOR",
                excepcion.getMessage()
        );
    }

    @Test
    void deberiaAprobarSolicitudEnviada() {

        Solicitud solicitud =
                new Solicitud("INC-002", "Incidencia", Estado.ENVIADA);

        Solicitud resultado = solicitud.aprobar();

        assertEquals("INC-002", resultado.id());
        assertEquals("Incidencia", resultado.tipo());
        assertEquals(Estado.APROBADA, resultado.estado());
    }

    @Test
    void noDeberiaAprobarSolicitudSiNoEstaEnviada() {

        Solicitud solicitud =
                new Solicitud("INC-002", "Incidencia", Estado.BORRADOR);

        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                solicitud::aprobar
        );

        assertEquals(
                "solo se aprueba una solicitud ENVIADA",
                excepcion.getMessage()
        );
    }

    @Test
    void deberiaRechazarSolicitudEnviada() {

        Solicitud solicitud =
                new Solicitud("INC-002", "Incidencia", Estado.ENVIADA);

        Solicitud resultado = solicitud.rechazar();

        assertEquals("INC-002", resultado.id());
        assertEquals("Incidencia", resultado.tipo());
        assertEquals(Estado.RECHAZADA, resultado.estado());
    }

    @Test
    void noDeberiaRechazarSolicitudSiNoEstaEnviada() {

        Solicitud solicitud =
                new Solicitud("INC-002", "Incidencia", Estado.BORRADOR);

        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                solicitud::rechazar
        );

        assertEquals(
                "solo se rechaza una solicitud ENVIADA",
                excepcion.getMessage()
        );
    }
}

