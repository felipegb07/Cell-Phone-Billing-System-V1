package InterfazPrincipal.InterfazEjecucion;

import java.time.LocalDate;

public class LlamadaInternacional extends Llamada{
    private String destino;

    /*Constructor*/

    public LlamadaInternacional(long duracion, LocalDate fecha, long telefonoDestino, long valor, String destino) {
        super(duracion, fecha, telefonoDestino, valor);
        this.destino = destino;
    }

    /*Metodo*/
    public long calcularValor(){

        return 1;
    }

    /*Getter y setter del atributo que hay*/
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
