package InterfazPrincipal;

import java.util.ArrayList;

public abstract class Cuenta {
    private long id;
    private long numero;
    private ArrayList<Llamada> llamadasCliente;

    /*Constructor*/
    public Cuenta(long id, long numero, ArrayList<Llamada> llamadasCliente) {
        this.id = id;
        this.numero = numero;
        this.llamadasCliente = llamadasCliente;
    }

    /*Métodos*/
    public long obtenerPagoCuenta(){

        return 1;
    }

    /*Getters y setters*/
}
