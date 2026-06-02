package InterfazPrincipal.InterfazEjecucion;

import InterfazPrincipal.ENUMS.Paises;
import InterfazPrincipal.Excepciones.CuentaNoEncontradaException;
import InterfazPrincipal.Excepciones.PaisNoEncontradoException;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class TestConsola implements Serializable {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;
        Utils modDeUtilidades = new Utils();
        IEmpresa miEmpresa = new Empresa();
        ManejoArchivos archivosGenerales = new ManejoArchivos();
        do {
            System.out.println("\n=============================================");
            System.out.println("Ingrese la opción que se quiere implementar");
            System.out.println("1. Ingresar clientes.\n" +
                    "2. Agregar nueva cuenta prepago o postapago.\n" +
                    "3. Agregar una nueva llamada nacional o internacional\n" +
                    "4. Agregar una recarga (Solo cuentas prepago)\n" +
                    "5. Reporte de facturación postpago a fin de mes.\n" +
                    "6. Reporte de recargas a fin de mes.\n" +
                    "7. Guardar la empresa en un archivo como un objeto\n" +
                    "8. Cargar un archivo en el objeto empresa\n" +
                    "9. Salir\n" +
                    "=============================================");
            System.out.print("Opción: ");
            int opcion = entrada.nextInt();
            entrada.nextLine(); // limpiar buffer
            boolean retorno = false;

            switch (opcion) {
                case 1:
                    do {
                        System.out.println("\n--- CARGAR CLIENTES ---");
                        System.out.println("1. Seleccionar archivo de clientes");
                        System.out.println("9. Regresar al menú principal");
                        System.out.print("Seleccione una opción: ");
                        int subOpcion = entrada.nextInt();
                        entrada.nextLine(); // limpiar buffer

                        if (subOpcion == 1) {
                            System.out.println("Procesando archivo...");
                            lecturaDatos(entrada, miEmpresa);
                        } else if (subOpcion == 9) {
                            retorno = true;
                        }
                    } while (!retorno);
                    break;

                case 2:
                    System.out.println("\n---AGREGAR NUEVA CUENTA PREPAGO O POSTPAGO---");
                    agregarCuenta(entrada, miEmpresa);
                    break;

                case 3:
                    System.out.println("\n---AGREGAR NUEVA LLAMADA NACIONAL O INTERNACIONAL---");
                    registrarLlamada(entrada, miEmpresa, modDeUtilidades);
                    break;

                case 4:
                    System.out.println("\n---AGREGAR UNA RECARGA---");
                    System.out.println("Ingrese su numero de telefono:");
                    long numeroTelefono = entrada.nextLong();
                    entrada.nextLine(); // limpiar buffer
                    agregarRecarga(entrada, numeroTelefono, miEmpresa, modDeUtilidades);
                    break;

                case 5:
                    System.out.println("\n---REPORTE DE FACTURACION POSTPAGO A FIN DE MES---");
                    reporteFacturacionPostpago(miEmpresa, modDeUtilidades);
                    break;

                case 6:
                    System.out.println("\n---REPORTE DE RECARGAS A FIN DE MES---");
                    reporteRecargasGenerales(miEmpresa, modDeUtilidades);
                    break;

                case 7:
                    System.out.println("\n---GUARDAR EMPRESA EN UN ARCHIVO COMO OBJETO---");
                    System.out.println("Ingrese la ruta del archivo: ");
                    String archivo = entrada.nextLine();
                    archivosGenerales.serializarDatosEnSistema(miEmpresa, archivo);
                    break;

                case 8:
                    System.out.println("\n---CARGA DE UN ARCHIVO EL OBJETO EMPRESA---");
                    System.out.println("Ingrese el archivo de donde quiere cargar los datos: ");
                    String nombre = entrada.nextLine();
                    IEmpresa cargada = archivosGenerales.cargarDatosDelSistema(miEmpresa, nombre);
                    if (cargada != null) {
                        miEmpresa = cargada;
                        System.out.println("Empresa cargada correctamente.");
                    } else {
                        System.out.println("Error: No se pudo cargar la empresa.");
                    }
                    break;

                case 9:
                    System.out.println("---SALIDA DEL MENU---");
                    continuar = false;
                    break;
                
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while(continuar);
    }

    /*Funciones para evitar la saturación del menú*/
    //Lectura de los datos
    public static void lecturaDatos(Scanner entrada, IEmpresa miEmpresa){
        System.out.println("Ingrese el nombre del archivo donde quiere hacer la lectura: ");
        String archivoLectura = entrada.nextLine();
        miEmpresa.lecturaClientes(archivoLectura);
    }

    //Creación de una cuenta prepago o postpago
    public static void agregarCuenta(Scanner entrada, IEmpresa miEmpresa){
        System.out.println("Ingrese el nombre del cliente:");
        String nombreCliente = entrada.nextLine();
        System.out.println("Ingrese el número de teléfono:");
        long numeroTelefono = entrada.nextLong();
        entrada.nextLine(); // limpiar buffer
        miEmpresa.agregarCuenta(entrada, nombreCliente, numeroTelefono);
    }

    //Registro de llamada delegando a la empresa
    public static void registrarLlamada(Scanner entrada, IEmpresa miEmpresa, Utils modDeUtilidades){
        miEmpresa.registrarLlamada(entrada, (Empresa) miEmpresa, modDeUtilidades);
    }

    //Agregar recarga
    public static void agregarRecarga(Scanner entrada, long numeroTelefono, IEmpresa miEmpresa, Utils modDeUtilidades){
         try {
             Prepago cuentaPrepago = modDeUtilidades.buscarCuentaPrepago(numeroTelefono, miEmpresa);
             if(cuentaPrepago != null){
                 System.out.println("Indique la fecha de la recarga en este formato AAAA-MM-DD: ");
                 String fecha = entrada.nextLine();
                 LocalDate fechaRecarga = LocalDate.parse(fecha);

                 System.out.println("Indique el valor de la recarga: ");
                 long valor = entrada.nextLong();
                 entrada.nextLine(); // limpiar buffer
                 Recarga recargaUsuario = new Recarga(fechaRecarga, valor);

                 if (cuentaPrepago.getRecargas() == null) {
                     cuentaPrepago.setRecargas(new ArrayList<>());
                 }
                 cuentaPrepago.getRecargas().add(recargaUsuario);

                 // Se adicionan minutos a una tasa de $100 por minuto (por ejemplo)
                 long minutosAdicionales = valor / 100;
                 cuentaPrepago.setNumeroMinutos(cuentaPrepago.getNumeroMinutos() + minutosAdicionales);

                 System.out.println("Recarga realizada exitosamente.");
                 System.out.println("Se adicionaron " + minutosAdicionales + " minutos. Minutos totales: " + cuentaPrepago.getNumeroMinutos());
             } else {
                 throw new CuentaNoEncontradaException("Cuenta no encontrada...");
             }
         } catch (CuentaNoEncontradaException e){
             System.out.println(e.getMessage());
         } catch (Exception e){
             System.out.println("Error al realizar la recarga: " + e.getMessage());
         }
    }

    //Reporte de recargas para cuentas Prepago
    public static void reporteRecargasGenerales(IEmpresa miEmpresa, Utils modDeUtilidades) {
        ArrayList<Prepago> todasLasCuentas = modDeUtilidades.buscarTodasLasCuentasPrepago(miEmpresa);

        if (todasLasCuentas.isEmpty()) {
            System.out.println("No hay cuentas Prepago registradas en la empresa.");
            return;
        }

        for (Prepago cuenta : todasLasCuentas) {
            System.out.println("\nNúmero cuenta: " + cuenta.getNumero() + " | Minutos actuales: " + cuenta.getNumeroMinutos());
            ArrayList<Recarga> recargasUsuario = cuenta.getRecargas();

            if (recargasUsuario == null || recargasUsuario.isEmpty()) {
                System.out.println("  Sin recargas registradas.");
            } else {
                for (Recarga r : recargasUsuario) {
                    System.out.println("  - Fecha: " + r.getFecha() + " | Valor: $" + r.getValor());
                }
            }
        }
    }

    //Reporte de facturación para cuentas Postpago
    public static void reporteFacturacionPostpago(IEmpresa miEmpresa, Utils modDeUtilidades) {
        ArrayList<Cliente> listaClientes = miEmpresa.getClientes();
        if (listaClientes == null || listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        boolean hayPostpago = false;
        for (Cliente c : listaClientes) {
            Cuenta cuenta = c.getCuentaCliente();
            if (cuenta instanceof Postpago) {
                hayPostpago = true;
                Postpago post = (Postpago) cuenta;
                long totalFactura = post.obtenerPagoCuenta();
                System.out.println("\nCliente: " + c.getNombre() + " (Cédula: " + c.getIdentificacion() + ")");
                System.out.println("  Número cuenta: " + post.getNumero());
                System.out.println("  Cargo fijo: $" + post.getCargoFijo());
                System.out.println("  Llamadas realizadas:");
                if (post.getLlamadasCliente() == null || post.getLlamadasCliente().isEmpty()) {
                    System.out.println("    Sin llamadas registradas.");
                } else {
                    for (Llamada l : post.getLlamadasCliente()) {
                        System.out.println("    - Destino: " + l.getTelefonoDestino() + " | Fecha: " + l.getFecha() + " | Duración: " + l.getDuracion() + " min | Valor: $" + l.getValor());
                    }
                }
                System.out.println("  TOTAL A PAGAR: $" + totalFactura);
            }
        }
        if (!hayPostpago) {
            System.out.println("No hay cuentas Postpago registradas.");
        }
    }
}
