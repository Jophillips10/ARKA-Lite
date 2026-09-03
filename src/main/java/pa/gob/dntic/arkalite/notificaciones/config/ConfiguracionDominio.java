package pa.gob.dntic.arkalite.notificaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pa.gob.dntic.arkalite.notificaciones.adaptadores.salida.RepositorioEnMemoria;
import pa.gob.dntic.arkalite.notificaciones.dominio.RepositorioDeNotificaciones;
import pa.gob.dntic.arkalite.notificaciones.dominio.ServicioDeNotificaciones;

@Configuration
public class ConfiguracionDominio {

    @Bean
    public RepositorioDeNotificaciones repositorioDeNotificaciones() {
        return new RepositorioEnMemoria();
    }

    @Bean
    public ServicioDeNotificaciones servicioDeNotificaciones(RepositorioDeNotificaciones repositorio) {
        return new ServicioDeNotificaciones(repositorio);
    }
}
