package pa.gob.dntic.arkalite.notificaciones.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pa.gob.dntic.arkalite.notificaciones.dominio.RepositorioDeNotificaciones;
import pa.gob.dntic.arkalite.notificaciones.dominio.ServicioDeNotificaciones;

@Configuration
public class ConfiguracionDominioNotificaciones {

    @Bean
    public ServicioDeNotificaciones servicioDeNotificaciones(RepositorioDeNotificaciones publicador){
        return new ServicioDeNotificaciones(publicador);
    }

    //datos de ejemplo para poder ver /solicitudes sin post (los writes llegan a etapas futuras)
    @Bean
    public CommandLineRunner notificacionesEjemplo(ServicioDeNotificaciones publicador){
        return args ->{
            publicador.publicar("Notificacion1 - prueba");
            publicador.publicar("Notificacion2 - prueba");
            publicador.publicar("Notificacion3 - prueba");
        };
    }
}
