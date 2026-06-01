package InterfazPrincipal.InterfazEjecucion;

import java.util.ArrayList;

/*Bueno de momento tengo entendido que acá se hace la muestra
* de los servicios que ofrece la empresa creería yo
* además le metí algo de cositas medio raras por ahí de
* comentarios para el que se digne a leer esto*/

public interface IEmpresa {
    ArrayList getClientes();

    ArrayList<Cliente> lecturaClientes(String nombreArchivo);
}
