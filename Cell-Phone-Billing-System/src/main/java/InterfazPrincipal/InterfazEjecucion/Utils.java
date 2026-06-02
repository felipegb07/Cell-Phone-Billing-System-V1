package InterfazPrincipal.InterfazEjecucion;

import InterfazPrincipal.ENUMS.Paises;
import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Utils {
    public static long CONSECUTIVO = 1;

    public String buscarPais(String nombre){
        for(Paises p : Paises.values()){
            if(p.getNombre().equalsIgnoreCase(nombre)){
                return p.getExtensionTelefonica();
            }
        }
        return null;
    }

    public LocalDateTime modificaFecha(LocalDateTime fechaLlamada, int años, int meses, int dias, int horas, int minutos, int segundos){
        fechaLlamada.plusYears(años).plusMonths(meses).plusDays(dias).plusMinutes(minutos).plusSeconds(segundos);
        return fechaLlamada;
    }

    /*Esto se puede optimizar para que al momento de generar la busqeda nos arroje si es
    * o no una cuenta prepago*/
    //Busqueda de una cuenta con su numero
    public Prepago buscarCuentaPrepago(long numeroTelefono, IEmpresa miEmpresa) {
        ArrayList<Cliente> listaClientes = miEmpresa.getClientes();

        for (Cliente c : listaClientes) {
            Cuenta cuentaActual = c.getCuentaCliente();
            if (cuentaActual != null) {
                if (cuentaActual.getNumero() == numeroTelefono && cuentaActual instanceof Prepago) {
                    return (Prepago)cuentaActual;
                }
            }
        }
        return null;
    }

    public ArrayList<Prepago> buscarTodasLasCuentasPrepago(IEmpresa miEmpresa) {
        ArrayList<Cliente> listaClientes = miEmpresa.getClientes();

        ArrayList<Prepago> cuentasPrepago = new ArrayList<>();

        for (Cliente c : listaClientes) {
            Cuenta cuentaActual = c.getCuentaCliente();
            if (cuentaActual != null) {
                // Si la cuenta es Prepago, la agregamos a nuestra lista
                if (cuentaActual instanceof Prepago) {
                    cuentasPrepago.add((Prepago) cuentaActual);
                }
            }
        }
        // Retornamos la lista (puede ir vacía si no hay ninguna)
        return cuentasPrepago;
    }

}
