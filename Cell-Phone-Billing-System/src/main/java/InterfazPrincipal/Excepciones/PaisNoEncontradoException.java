package InterfazPrincipal.Excepciones;

public class PaisNoEncontradoException extends RuntimeException {
    public PaisNoEncontradoException(String message) {
        super("El país que ha buscado no se encuentra...");
    }
}