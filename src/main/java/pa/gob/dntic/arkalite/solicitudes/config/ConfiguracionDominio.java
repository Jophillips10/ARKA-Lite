package pa.gob.dntic.arkalite.solicitudes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pa.gob.dntic.arkalite.solicitudes.dominio.RepositorioDeSolicitudes;
import pa.gob.dntic.arkalite.solicitudes.dominio.ServicioDeSolicitudes;

//define como se INSTANCIA el servicio como beans
// contenedor se llena con frijoles (beans) un beans es un modulo. fragmento de una aplicación.
@Configuration
public class ConfiguracionDominio {

    @Bean
    public ServicioDeSolicitudes servicioDeSolicitudes(RepositorioDeSolicitudes repositorio){
        return new ServicioDeSolicitudes(repositorio);
    }

    //datos de ejemplo para poder ver /solicitudes sin post (los writes llegan a etapas futuras)
    @Bean
    public CommandLineRunner datosDeEjemplo(ServicioDeSolicitudes servicio){
        return args ->{
            servicio.registrar("INC-001","Incidente");
            servicio.registrar("CAM-002","Cambio");
            servicio.enviar("CAM-002");
        };
    }
}
