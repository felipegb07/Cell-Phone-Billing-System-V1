package InterfazPrincipal.InterfazEjecucion;

import InterfazPrincipal.ENUMS.Paises;
import InterfazPrincipal.Excepciones.ClienteNoEncontradoException;
import InterfazPrincipal.Excepciones.PaisNoEncontradoException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class Empresa implements IEmpresa, Serializable {
    private static final long serialVersionUID = 1L;
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
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }


    //Metodo para poder hacer la lectura de los archivos
    @Override
    public void lecturaClientes(String nombreArchivo) {
        HashSet<Cliente> hashClientes = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;

            while((linea = reader.readLine()) != null){
                linea = linea.trim();

                // Rompemos el bucle al final o si está vacío
                if(linea.equals("#FIN")){
                    break;
                }

                // Ignorar líneas vacías o comentarios de cabecera
                if(linea.isEmpty() || linea.startsWith("#")){
                    continue;
                }

                String[] partes = linea.split("\\*");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String identificacion = partes[1].trim();
                    String direccion = partes[2].trim();
                    Cliente clienteNuevo = new Cliente(nombre, identificacion, direccion);
                    hashClientes.add(clienteNuevo);
                }
            }
        } catch(IOException e){
            System.out.println("ERROR EN LA LECTURA DEL ARCHIVO " + e.getMessage());
        }

        // Asignamos los clientes cargados
        this.clientes = new ArrayList<>(hashClientes);

        // Ordenamiento burbuja de los clientes por identificación
        int n = this.clientes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String c1 = this.clientes.get(j).getIdentificacion();
                String c2 = this.clientes.get(j + 1).getIdentificacion();

                if (c1.compareTo(c2) > 0) {
                    Cliente temp = this.clientes.get(j);
                    this.clientes.set(j, this.clientes.get(j + 1));
                    this.clientes.set(j + 1, temp);
                }
            }
        }
    }


    //Agregar cuenta
    @Override
    public void agregarCuenta(Scanner entrada, String nombre, long numeroTelefono){
        try{
            if (clientes == null) {
                clientes = new ArrayList<>();
            }
            boolean encontrado = false;
            for(Cliente c: clientes){
                if(c.getNombre().equals(nombre)){
                    encontrado = true;
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
                            cuentaPrepago.setLlamadasCliente(new ArrayList<>());
                            cuentaPrepago.setNumeroMinutos(5);
                            cuentaPrepago.setRecargas(new ArrayList<>());
                            cuentaPrepago.setTipoCuenta("prepago");

                            c.setCuentaCliente(cuentaPrepago);
                            valido = true;
                        }

                        else if(tipo.equals(2)){
                            System.out.println("---POSTPAGO---");
                            Postpago cuentaPostpago = new Postpago();
                            long id = cuentaPostpago.getId();

                            //Llenado de los datos para la cuenta
                            cuentaPostpago.setCargoFijo(20000);
                            cuentaPostpago.setId(id);
                            cuentaPostpago.setNumero(numeroTelefono);
                            cuentaPostpago.setLlamadasCliente(new ArrayList<>());
                            cuentaPostpago.setTipoCuenta("postpago");

                            c.setCuentaCliente(cuentaPostpago);
                            valido = true;
                        } else {
                            System.out.println("Opción inválida. Seleccione el tipo de cuenta: \n\t1. Prepago \n\t2. Postpago");
                            tipo = entrada.nextInt();
                            entrada.nextLine();
                        }
                    }while(!valido);
                    break;
                }
            }
            if(!encontrado){
                throw new ClienteNoEncontradoException("Cliente no encontrado...");
            }
        } catch (ClienteNoEncontradoException e){
            System.out.println(e.getMessage());
        } catch (Exception e){ //Excepcion genérica en caso de fallo
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void registrarLlamada(Scanner entrada, Empresa miEmpresa, Utils modDeUtilidades){
        System.out.println("Ingrese el número de teléfono de origen (su cuenta):");
        long numeroOrigen = entrada.nextLong();
        entrada.nextLine(); // Limpieza del buffer

        Cuenta cuentaOrigen = null; //No deja crear una clase new Cuenta porque es abstract
        ArrayList<Cliente> arregloClientes = miEmpresa.getClientes();

        for(Cliente c : arregloClientes){
            Cuenta cuentaTemporal = c.getCuentaCliente();
            if(cuentaTemporal != null && cuentaTemporal.getNumero() == numeroOrigen) {
                cuentaOrigen = cuentaTemporal;
                break; // Ya encontramos la cuenta, salimos del ciclo
            }
        }

        // Si la cuenta no existe en el sistema para
        if(cuentaOrigen == null) {
            System.out.println("Error: El número de teléfono ingresado no existe.");
            return;
        }

        if (cuentaOrigen instanceof Prepago) {
            Prepago cuentaPrepago = (Prepago) cuentaOrigen;
            System.out.println("Cuenta identificada como PREPAGO.");

            if (cuentaPrepago.getNumeroMinutos() <= 0) {
                System.out.println("No tiene la cantidad de minutos necesaria...");
                return;
            }
        }else{ //En caso tal de no ser prepago con el instanseof se va a postpago automáticamente
            System.out.println("Cuenta identificada como POSTPAGO.");
        }

        //Continuación normal del flujo del tipo de llamada
        System.out.println("Ingrese el tipo de llamada \n\t1. Nacional\n\t2. Internacional");
        Integer tipo = entrada.nextInt();
        entrada.nextLine(); // Limpieza del buffer

        while(!tipo.equals(1) && !tipo.equals(2)){
            System.out.println("Opción inválida. Ingrese el tipo de llamada \n\t1. Nacional\n\t2. Internacional");
            tipo = entrada.nextInt();
            entrada.nextLine(); // Limpieza del buffer dentro del while
        }

        if(tipo.equals(2)){
            System.out.println("---LLAMADA INTERNACIONAL---");
            System.out.println("Ingrese el nombre del pais de destino");
            try {
                String nombre = entrada.nextLine();

                String paisStr = modDeUtilidades.buscarPais(nombre);
                if (paisStr == null) {
                    throw new PaisNoEncontradoException("El país que ha buscado no se encuentra...");
                }
                Paises paisDestino = Paises.valueOf(paisStr);
                System.out.println("País válido para llamada: " + paisDestino.getNombre() + "\nExtención: " + paisDestino.getExtensionTelefonica());

                LocalDateTime fechaLlamada = LocalDateTime.now();
                System.out.println("Fecha de la llamada: " + fechaLlamada);

                System.out.println("Ingrese el telefono de destino sin colocar la extención " + paisDestino.getExtensionTelefonica());
                String telefonoDestino = entrada.nextLine();

                telefonoDestino = paisDestino.getExtensionTelefonica().concat("-").concat(telefonoDestino);

                System.out.println("Ingrese la duración de la llamada en minutos: ");
                long adicion = entrada.nextInt();
                entrada.nextLine(); // Limpieza del buffer

                System.out.println("Ingrese el valor por minuto de la llamada ");
                long valor = entrada.nextInt();
                entrada.nextLine();

                System.out.println("Ingrese el porcentaje de cobro sobre el valor de la llamada");
                long porcentaje = entrada.nextInt();
                entrada.nextLine();

                System.out.print("Valor de la llamada: ");
                // Usando tu constructor vacío tal como lo imaginamos
                LlamadaInternacional valorLlamadaInter = new LlamadaInternacional();
                long valorFinal = valorLlamadaInter.calcularValor(porcentaje, valor, adicion);
                System.out.println("$" + valorFinal);

                LlamadaInternacional llamadaUsuario = new LlamadaInternacional(adicion, LocalDate.now(), telefonoDestino, valorFinal, paisDestino.getNombre());

                cuentaOrigen.getLlamadasCliente().add(llamadaUsuario);

                // Perpago o las 1500
                if (cuentaOrigen instanceof Prepago) {
                    Prepago cuentaPrepago = (Prepago) cuentaOrigen;
                    long minutosRestantes = cuentaPrepago.getNumeroMinutos() - adicion;
                    cuentaPrepago.setNumeroMinutos(minutosRestantes);
                    System.out.println("Minutos restantes en su cuenta Prepago: " + minutosRestantes);
                }

                System.out.println("--- LLAMADA REGISTRADA EXITOSAMENTE ---");

            } catch (PaisNoEncontradoException e){
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        if(tipo.equals(1)){
            System.out.println("---LLAMADA NACIONAL---");
            System.out.println("Fecha actual " + LocalDate.now());
            System.out.println("Ingresa la duración de la llamada: ");
            long duracion = entrada.nextInt();
            entrada.nextLine();

            System.out.println("Ingrese el telefono de destino: ");
            String telefonoDestino = entrada.nextLine();

            System.out.println("Ingrese el valor por minuto: ");
            long valorMinuto = entrada.nextInt();
            entrada.nextLine();

            LlamadaNacional valorLlamada = new LlamadaNacional();
            long valorFinal = valorLlamada.calcularValor(duracion, valorMinuto);
            System.out.println("$" + valorFinal);

            LlamadaNacional llamadaUsuario = new LlamadaNacional(duracion, LocalDate.now(), telefonoDestino, valorMinuto);
            cuentaOrigen.getLlamadasCliente().add(llamadaUsuario);

            if (cuentaOrigen instanceof Prepago) {
                Prepago cuentaPrepago = (Prepago) cuentaOrigen;
                long minutosRestantes = cuentaPrepago.getNumeroMinutos() - duracion;
                cuentaPrepago.setNumeroMinutos(minutosRestantes);
                System.out.println("Minutos restantes en su cuenta Prepago: " + minutosRestantes);
            }

        }
    }

}