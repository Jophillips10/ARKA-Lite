package pa.gob.dntic.arkalite.solicitudes.dominio;

import pa.gob.dntic.arkalite.notificaciones.dominio.RepositorioDeNotificaciones;

import java.util.List;

public class ServicioDeSolicitudes {

    private final RepositorioDeSolicitudes repositorio;
    private final RepositorioDeNotificaciones publicador;

    public ServicioDeSolicitudes(RepositorioDeSolicitudes repository, RepositorioDeNotificaciones publicador){
        this.repositorio = repository;
        this.publicador = publicador;
    }

    public Solicitud registrar(String id, String tipo){
        // TODO: crear una solicitud en Borrador, guardarla en el repositorio y devolverla
        Solicitud nuevaSolicitud = new Solicitud(id,tipo,Estado.BORRADOR);
        repositorio.guardar(nuevaSolicitud);
        return nuevaSolicitud;

    }

    public Solicitud enviar(String id){
        // TODO: buscar la solicitud, llamar a su enviar(), guardarla y devolverla
        Solicitud solicitudEnviada = buscar(id).enviar();
        repositorio.guardar(solicitudEnviada);
        return solicitudEnviada;

    }

    public Solicitud aprobar(String id){
        // TODO: buscar la solicitud, llamar a su enviar(), guardarla y devolverla
        Solicitud solicitudAprobada = buscar(id).aprobar();
        repositorio.guardar(solicitudAprobada);
        return solicitudAprobada;
    }

    public Solicitud rechazar(String id){
        // TODO: buscar la solicitud, llamar a su enviar(), guardarla y devolverla
        Solicitud solicitudRechazada = buscar(id).rechazar();
        repositorio.guardar(solicitudRechazada);
        return solicitudRechazada;
    }

    public List<Solicitud> listar(){
        // TODO: devolver todas las del repositorio
        return repositorio.todas();

    }

    public Solicitud buscar(String id){
        // TODO: devolver la del id, o lanzar, SolcitudNoEncontrada
        return repositorio.buscar(id).orElseThrow(() -> new SolicitudNoEncontrada(id));
    }



}
