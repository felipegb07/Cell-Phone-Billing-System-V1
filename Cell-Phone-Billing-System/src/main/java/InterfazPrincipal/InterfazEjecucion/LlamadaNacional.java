package InterfazPrincipal.InterfazEjecucion;

import java.io.Serializable;
import java.time.LocalDate;

public class LlamadaNacional extends Llamada implements Serializable {
    private static final long serialVersionUID = 1L;

    public LlamadaNacional(){}

    public LlamadaNacional(long duracion, LocalDate fecha, String telefonoDestino, long valor) {
        super(duracion, fecha, telefonoDestino, valor);
    }

    /*Meotodo*/
    public long calcularValor(long duracion, long valor){
        valor *= duracion;
        return valor;
    }
}
