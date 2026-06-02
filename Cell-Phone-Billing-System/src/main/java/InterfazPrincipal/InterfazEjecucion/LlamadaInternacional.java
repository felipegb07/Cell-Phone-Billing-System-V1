package InterfazPrincipal.InterfazEjecucion;

import java.io.Serializable;
import java.time.LocalDate;

public class LlamadaInternacional extends Llamada implements Serializable {
    private static final long serialVersionUID = 1L;
    private String destino;

    /*Constructor*/
    public LlamadaInternacional(){}

    public LlamadaInternacional(long duracion, LocalDate fecha, String telefonoDestino, long valor, String destino) {
        super(duracion, fecha, telefonoDestino, valor);
        this.destino = destino;
    }

    /*Metodo*/
    public long calcularValor(long porcentaje, long valor, long duracion){
        valor += valor * porcentaje / 100;
        valor *= duracion;
        return valor;
    }

    /*Getter y setter del atributo que hay*/
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
