package InterfazPrincipal.InterfazEjecucion;

import InterfazPrincipal.ENUMS.Paises;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Utils {
    //Métodos estaticos útilitarios públicos que se pueden usar de manera general en todas las clases
    public static long CONSECUTIVO = 1;

    public String buscarPais(String nombre){
        for(Paises p : Paises.values()){
            if(p.getNombre().equalsIgnoreCase(nombre)){
                return p.getExtensionTelefonica();
            }
        }
        return null;
    }
    //Lectura de datos
}
