package InterfazPrincipal.InterfazEjecucion;

import java.util.TreeSet;

public class Utils {
    //Métodos estaticos útilitarios públicos que se pueden usar de manera general en todas las clases
    private static long CONSECUTIVO;

    //1. Lectura del archivo que necesita el cliente
    /*Ejemplo de archivo:
    * -----------------------------------------------
    *   #CLIENTES
        #nombre------------cedula---------direccion
        Carlos Torres
        *1923455
        *calle 77 No. 34-56 – Bogota
        Maria Roa Lopez
        *4123455
        *calle 37 No. 24-56 – Cali
        Oswaldo Williams *1923777
        *calle 7 No. 3-56 – Bogota
        #FIN
     * ----------------------------------------------
     * */

    public static TreeSet<Cliente> lecturaArchivos(){
        //Implementación de una excepción en la lectura
        TreeSet<Cliente> arregloDeClientes = new TreeSet<Cliente>();
        //Lectura del cliente
        return arregloDeClientes;
    }
}
