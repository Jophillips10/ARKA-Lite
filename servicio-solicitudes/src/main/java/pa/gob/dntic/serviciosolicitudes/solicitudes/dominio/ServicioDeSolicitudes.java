package pa.gob.dntic.serviciosolicitudes.solicitudes.dominio;

import pa.gob.dntic.serviciosolicitudes.eventos.SolicitudEnviada;

import java.util.List;

public class ServicioDeSolicitudes {

    private final RepositorioDeSolicitudes repositorio;
    private final PublicadorDeEventos publicador;

    public ServicioDeSolicitudes(RepositorioDeSolicitudes repositorio, PublicadorDeEventos publicador) {
        this.repositorio = repositorio;
        this.publicador = publicador;
    }

    public Solicitud registrar(String id, String tipo) {
        Solicitud s = new Solicitud(id, tipo, Estado.BORRADOR);
        repositorio.guardar(s);
        return s;
    }

    public Solicitud enviar(String id) {
        Solicitud enviada = buscar(id).enviar();
        repositorio.guardar(enviada);
        publicador.publicar(new SolicitudEnviada(enviada.id(), enviada.tipo()));  // avisa; no sabe quién reacciona
        return enviada;
    }

    public List<Solicitud> listar() { return repositorio.todas(); }

    public Solicitud buscar(String id) {
        return repositorio.buscar(id).orElseThrow(() -> new SolicitudNoEncontrada(id));
    }
}

