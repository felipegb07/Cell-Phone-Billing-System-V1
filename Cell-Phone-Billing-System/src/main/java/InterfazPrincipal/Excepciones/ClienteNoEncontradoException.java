package InterfazPrincipal.Excepciones;

public class ClienteNoEncontradoException extends RuntimeException {
  public ClienteNoEncontradoException(String message) {
    super("Cliente no encontrado...");
  }
}
