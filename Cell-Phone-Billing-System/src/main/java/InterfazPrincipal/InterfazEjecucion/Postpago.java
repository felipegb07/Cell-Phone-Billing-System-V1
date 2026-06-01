package InterfazPrincipal.InterfazEjecucion;

import java.util.ArrayList;

public class Postpago extends Cuenta{
    private long cargoFijo;

    /*Constructor*/
    /*Vacio*/
    public Postpago(){}

    /*Lleno*/
    public Postpago(long id, long numero, ArrayList<Llamada> llamadasCliente, long cargoFijo) {
        super(id, numero, llamadasCliente);
        this.cargoFijo = cargoFijo;
    }

    public long getCargoFijo() {
        return cargoFijo;
    }

    public void setCargoFijo(long cargoFijo) {
        this.cargoFijo = cargoFijo;
    }

    /*Metodo*/
    @Override
    public long obtenerPagoCuenta(){

        return 1;
    }
}
