package InterfazPrincipal;

import java.util.ArrayList;

public class Postpago extends Cuenta{
    private long cargoFijo;

    /*Constructor*/
    public Postpago(long id, long numero, ArrayList<Llamada> llamadasCliente, long cargoFijo) {
        super(id, numero, llamadasCliente);
        this.cargoFijo = cargoFijo;
    }

    /*Metodo*/
    @Override
    public long obtenerPagoCuenta(){

        return 1;
    }
}
