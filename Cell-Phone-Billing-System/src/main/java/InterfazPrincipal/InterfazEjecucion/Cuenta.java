package InterfazPrincipal.InterfazEjecucion;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipoCuenta;
    private long id;
    private long numero;
    private ArrayList<Llamada> llamadasCliente;

    /*Constructor*/
    /*Constuctor vacio*/
    public Cuenta(){}

    /*Constructor lleno*/
    public Cuenta(long id, long numero, ArrayList<Llamada> llamadasCliente, String tipoCuenta) {
        this.id = Utils.CONSECUTIVO++;
        this.numero = numero;
        this.llamadasCliente = llamadasCliente;
        this.tipoCuenta = tipoCuenta;
    }

    /*Métodos*/
    public long obtenerPagoCuenta(){

        return 1;
    }

    /*Getters y setters*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public ArrayList<Llamada> getLlamadasCliente() {
        return llamadasCliente;
    }

    public void setLlamadasCliente(ArrayList<Llamada> llamadasCliente) {
        this.llamadasCliente = llamadasCliente;
    }

    public String getTipoCuenta() {return tipoCuenta;}

    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }
}
