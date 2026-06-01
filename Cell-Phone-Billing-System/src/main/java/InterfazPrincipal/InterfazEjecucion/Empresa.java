package InterfazPrincipal.InterfazEjecucion;

import InterfazPrincipal.Excepciones.ClienteNoEncontradoException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Empresa implements IEmpresa {
    private String nombre;
    private ArrayList<Cliente> clientes;

    /*Getters y setters*/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public ArrayList getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }


    //Metodo para poder hacer la lectura de los archivos
    @Override
    public void lecturaClientes(String nombreArchivo) {
        //Lectura de las primeras dos líneas del archivo de texto las cuales no presentan ninguna útilidad
        //El hash lo uso para sacar repetidos de una vez
        HashSet<Cliente> hashClientes = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            while((linea = reader.readLine()) != null){
                String[] partes = linea.split("\\*"); //Esto se hace para que pueda procesar el signo
                //Rompemos el while en caso de que aparezca el fin o una linea vacía
                if(linea.isEmpty() || linea.equals("#FIN")){
                    break;
                }

                //Lectura de datos de manera rápida y hermosa
                if(partes.length == 3){
                    //Hacemos la partición teniendo en cuenta la cantidad de partes
                    String nombre = partes[0];
                    String identificacion = partes[1];
                    String direccion = partes[2];
                    Cliente clienteNuevo = new Cliente(nombre, identificacion, direccion);
                    hashClientes.add(clienteNuevo);
                }
            }
        } catch(IOException e){
            System.out.println("ERROR EN LA LECTURA DEL ARCHIVO " + e.getMessage());
        } //Aquí podemos hacer la implementación de errores

        //Pasamos los datos a un arraylist para luego ordenarlos
        ArrayList<Cliente> clientes = new ArrayList<>(hashClientes);

        //Ordenamiento burbuja de una vez para mostrar los clientes luego
        /*Esto inicialmente lo iba a hacer con un for each pero no pude lol*/

        int n = clientes.size(); //Cantidad de clientes
        //Ordenamiento bubuja
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String c1 = clientes.get(j).getIdentificacion();
                String c2 = clientes.get(j + 1).getIdentificacion();

                // Comparamos cadenas (puedes usar Long.parseLong si son puramente numéricas)
                if (c1.compareTo(c2) > 0) {
                    // Intercambio (Swap)
                    Cliente temp = clientes.get(j);
                    clientes.set(j, clientes.get(j + 1));
                    clientes.set(j + 1, temp);
                }
            }
        }
    }

    //Agregar cuenta
    @Override
    public void agregarCuenta(Scanner entrada, String nombre, long numeroTelefono){
        try{
            for(Cliente c: clientes){
                if(c.getNombre().equals(nombre)){
                    System.out.println("Seleccione el tipo de cuenta: \n\t1. Prepago \n\t2. Postpago");
                    Integer tipo = entrada.nextInt();
                    entrada.nextLine(); //Limpieza buffer
                    //validamos la competencia del usuario con un validador
                    Boolean valido = false;
                    do{
                        if(tipo.equals(1)){
                            System.out.println("---PREPAGO---");
                            Prepago cuentaPrepago = new Prepago();
                            long id = cuentaPrepago.getId();

                            //Llenado de los datos para la cuenta
                            cuentaPrepago.setId(id);
                            cuentaPrepago.setNumero(numeroTelefono);
                            cuentaPrepago.setLlamadasCliente(null);
                            cuentaPrepago.setNumeroMinutos(5);
                            cuentaPrepago.setRecargas(null);

                            c.setCuentaCliente(cuentaPrepago);
                            valido = true;
                        }

                        if(tipo.equals(2)){
                            System.out.println("---POSTPAGO---");
                            Postpago cuentaPostpago = new Postpago();
                            long id = cuentaPostpago.getId();

                            //Llenado de los datos para la cuenta
                            cuentaPostpago.setCargoFijo(20000);
                            cuentaPostpago.setId(id);
                            cuentaPostpago.setNumero(numeroTelefono);
                            cuentaPostpago.setLlamadasCliente(null); //no hay, es nueva lol

                            c.setCuentaCliente(cuentaPostpago);
                            valido = true;
                        }
                    }while(!valido);
                }
            }
        } catch (ClienteNoEncontradoException e){
            System.out.println(e.getMessage());
        } catch (Exception e){ //Excepcion genérica en caso de fallo
            System.out.println(e.getMessage());
        }
    }
}
