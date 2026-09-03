package pa.gob.dntic.arkalite.solicitudes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pa.gob.dntic.arkalite.solicitudes.adaptadores.salida.RepositorioEnMemoria;
import pa.gob.dntic.arkalite.solicitudes.dominio.RepositorioDeSolicitudes;
import pa.gob.dntic.arkalite.solicitudes.dominio.ServicioDeSolicitudes;

@Configuration
public class ConfiguracionDominio {

    @Bean
    public RepositorioDeSolicitudes repositorioDeSolicitudes() {
        return new RepositorioEnMemoria();
    }

    @Bean
    public ServicioDeSolicitudes servicioDeSolicitudes(RepositorioDeSolicitudes repositorio) {
        return new ServicioDeSolicitudes(repositorio);
    }

    @Bean
    public CommandLineRunner datosDeEjemplo(ServicioDeSolicitudes servicio) {
        return args -> {
            servicio.registrar("INC-001", "Incidente");
            servicio.enviar("INC-001");
            servicio.aprobar("INC-001");
        };
    }
}
