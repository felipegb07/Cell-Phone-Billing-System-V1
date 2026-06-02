package InterfazPrincipal.InterfazEjecucion;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Llamada implements Serializable {
    private static final long serialVersionUID = 1L;
    private long duracion;
    private LocalDate fecha;
    private String telefonoDestino;
    private long valor;

    /*Constructor*/
    public Llamada(){}

    public Llamada(long duracion, LocalDate fecha, String telefonoDestino, long valor) {
        this.duracion = duracion;
        this.fecha = fecha;
        this.telefonoDestino = telefonoDestino;
        this.valor = valor;
    }

    /*Getters y setters*/
    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTelefonoDestino() {
        return telefonoDestino;
    }

    public void setTelefonoDestino(String telefonoDestino) {
        this.telefonoDestino = telefonoDestino;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }
}
