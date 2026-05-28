package InterfazPrincipal.InterfazEjecucion;

import java.time.LocalDate;

public class LlamadaNacional extends Llamada{
    /*No hay atributos presentes en esta clase*/

    /*Implementación del constructor*/
    public LlamadaNacional(long duracion, LocalDate fecha, long telefonoDestino, long valor) {
        super(duracion, fecha, telefonoDestino, valor);
    }

    /*Meotodo*/
    public long calcularValor(){

        return 1;
    }
}
