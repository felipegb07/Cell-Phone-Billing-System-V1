package InterfazPrincipal.Excepciones;

public class CuentaNoEncontradaException extends RuntimeException {
    public CuentaNoEncontradaException(String message) {
        super("Cuenta no enontrada...");
    }
}
