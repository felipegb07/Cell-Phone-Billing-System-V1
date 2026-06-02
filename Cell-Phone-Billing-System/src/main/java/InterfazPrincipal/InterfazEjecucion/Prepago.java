package InterfazPrincipal.InterfazEjecucion;

import java.io.Serializable;
import java.util.ArrayList;

public class Prepago extends Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    private long numeroMinutos;
    private ArrayList<Recarga> recargas;

    /*Constructor*/
    /*Vacio*/
    public Prepago(){}

    /*Lleno*/
    public Prepago(long id, long numero, ArrayList<Llamada> llamadasCliente, long numeroMinutos, ArrayList<Recarga> recargas, String tipoCuenta) {
        super(id, numero, llamadasCliente, tipoCuenta);
        this.numeroMinutos = numeroMinutos;
        this.recargas = recargas;
    }

    /*Getters y setters*/
    public long getNumeroMinutos() {
        return numeroMinutos;
    }

    public void setNumeroMinutos(long numeroMinutos) {
        this.numeroMinutos = numeroMinutos;
    }

    public ArrayList<Recarga> getRecargas() {
        return recargas;
    }

    public void setRecargas(ArrayList<Recarga> recargas) {
        this.recargas = recargas;
    }
}
