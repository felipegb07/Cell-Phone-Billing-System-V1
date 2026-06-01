package InterfazPrincipal.InterfazEjecucion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Empresa implements IEmpresa {
    private String nombre;
    private HashSet<Cliente> clientes;

    /*Getters y setters*/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public HashSet<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashSet<Cliente> clientes) {
        this.clientes = clientes;
    }
    //Metodo para poder hacer la lectura de los archivos

    @Override
    public HashSet<Cliente> lecturaClientes(String nombreArchivo) {
        //Lectura de las primeras dos lineas del archivo de texto las cuales no presentan ninguna útilidad
        HashSet<Cliente> hashClientes = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            while((linea = reader.readLine()) != null){
                String[] partes = linea.split("\\*"); //Esto se hace para que pueda procesar el signo
                if(partes.length == 3){
                    //Hacemos la partición teniendo en cuenta la cantidad de partes
                    String nombre = partes[0];
                    String identificacion = partes[1];
                    String direccion = partes[2];
                    Cliente clienteNuevo = new Cliente(nombre, identificacion, direccion);
                    hashClientes.add(clienteNuevo);
                }
            }
            return hashClientes;
        } catch(IOException e){
            System.out.println("ERROR EN LA LECTURA DEL ARCHIVO " + e.getMessage());
        } //Aquí podemos hacer la implementación de errores
        return null;
    }
}
