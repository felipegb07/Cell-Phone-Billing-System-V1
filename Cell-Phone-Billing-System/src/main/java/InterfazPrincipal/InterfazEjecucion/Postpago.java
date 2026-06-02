package InterfazPrincipal.InterfazEjecucion;

import java.io.Serializable;
import java.util.ArrayList;

public class Postpago extends Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    private long cargoFijo;

    /*Constructor*/
    /*Vacio*/
    public Postpago(){}

    /*Lleno*/
    public Postpago(long id, long numero, ArrayList<Llamada> llamadasCliente, long cargoFijo, String tipoCuenta) {
        super(id, numero, llamadasCliente, tipoCuenta);
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
        long total = cargoFijo;
        if (getLlamadasCliente() != null) {
            for (Llamada l : getLlamadasCliente()) {
                total += l.getValor();
            }
        }
        return total;
    }
}
