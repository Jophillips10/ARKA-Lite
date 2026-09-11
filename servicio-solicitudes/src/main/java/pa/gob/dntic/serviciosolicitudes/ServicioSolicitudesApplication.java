package pa.gob.dntic.serviciosolicitudes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServicioSolicitudesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioSolicitudesApplication.class, args);
    }

    //@Bean
    //CommandLineRunner suscribirNotificaciones(BusDeEventosEnMemoria bus, ManejadorDeEventos manejador) {
    //    return args -> bus.suscribir(manejador::manejar);
    //}
}
