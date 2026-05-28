package InterfazPrincipal.InterfazEjecucion;

import java.time.LocalDate;

public abstract class Llamada {
    private long duracion;
    private LocalDate fecha;
    private long telefonoDestino;
    private long valor;

    /*Constructor*/
    public Llamada(long duracion, LocalDate fecha, long telefonoDestino, long valor) {
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

    public long getTelefonoDestino() {
        return telefonoDestino;
    }

    public void setTelefonoDestino(long telefonoDestino) {
        this.telefonoDestino = telefonoDestino;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }
}
