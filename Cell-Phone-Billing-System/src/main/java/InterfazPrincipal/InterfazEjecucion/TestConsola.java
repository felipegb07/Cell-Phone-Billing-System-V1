package InterfazPrincipal.InterfazEjecucion;

import InterfazPrincipal.ENUMS.Paises;
import InterfazPrincipal.Excepciones.CuentaNoEncontradaException;
import InterfazPrincipal.Excepciones.PaisNoEncontradoException;
import com.sun.tools.jdeprscan.scan.Scan;
import jdk.internal.org.jline.utils.OSUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class TestConsola{
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        Boolean salirAMenu = false;
        Utils modDeUtilidades = new Utils();
        do {
            System.out.println("Ingrese la opción que se quiere implementar");
            System.out.println("1. Ingresar clientes.\n" +
                    "2. Agregar nueva cuenta prepago o postapago.\n" +
                    "3. Agregar una nueva llamada nacional o internacional\n" +
                    "4. Agregar una recarga (Solo cuentas postpago)\n" +
                    "5. Reporte de facturación postpago a fin de mes.\n" +
                    "6. Reporte de recargas a fin de mes.\n" +
                    "7. Guardar la empresa en un archivo como un objeto\n" +
                    "8. Cargar un archivo en el objeto empresa\n" +
                    "9. Salir" +
                    "Opción: ");
            int opcion = entrada.nextInt();
            entrada.nextLine();
            Boolean retorno = false;
            IEmpresa miEmpresa = new Empresa();
            entrada.nextLine();


            switch (opcion) {
                case 1:
                    do {
                        System.out.println("--- CARGAR CLIENTES ---");
                        System.out.println("1. Seleccionar archivo de clientes");
                        System.out.println("9. Regresar al menú principal");
                        System.out.print("Seleccione una opción: ");
                        int subOpcion = entrada.nextInt();

                        //Podemos hacer la implementación de una excepción aquí
                        if (subOpcion == 1) {
                            //Hacemos la lecura de los datos desde una función implementada abajo
                            System.out.println("Procesando archivo...");

                            lecturaDatos(entrada, miEmpresa);
                        } else if (subOpcion == 9) { //Esto siempre será verdadero lol
                            retorno = true;
                        }
                    }while (!retorno);
                    salirAMenu = true;
                    break;
                case 2:
                    System.out.println("---AGREGAR NUEVA CUENTA PREPAGO O POSTPAGO---");
                    agregarCuenta(entrada, miEmpresa);
                    salirAMenu =true;
                    break;
                case 3:
                    System.out.println("---AGREGAR NUEVA LLAMADA NACIONAL O INTERNACIONAL---");
                    registrarLlamada(entrada, miEmpresa, modDeUtilidades);
                    break;

                case 4:
                    System.out.println("---AGREGAR UNA RECARGA---");
                    System.out.println("Ingrese su numero de telefono");
                    long numeroTelefono = entrada.nextInt();
                    entrada.nextLine(); //limpiamos el buffer
                    agregarRecarga(entrada, numeroTelefono, miEmpresa, modDeUtilidades);

                    salirAMenu = true;
                    break;

                case 5:
                    System.out.println("---REPORTE DE FACTURACION POSTPAGO A FIN DE MES---");
                    entrada.nextLine(); //limpieza del buffer

                    salirAMenu = true;
                    break;

                case 6:
                    System.out.println("---REPORTE DE RECARGAS A FIN DE MES---");
                    reporteRecargasGenerales(miEmpresa, modDeUtilidades);
                    salirAMenu = true;
                    break;

                case 7:
                    System.out.println("---GUARDAR EMPRESA EN UN ARCHIVO COMO OBJETO---");
                    break;

                case 8:
                    System.out.println("---CARGA DE UN ARFCHIVO EL OBJETO EMPRESA---");
                    break;

                case 9:
                    System.out.println("---SALIDA DEL MENU---");
                    salirAMenu = false;
            }
        }while(salirAMenu);
    }

    /*Funciones para evitar la saturación del menú*/
    //Lectura de los datos
    public static void lecturaDatos(Scanner entrada, IEmpresa miEmpresa){
        System.out.println("Ingrese el nombre del archivo donde quiere hacer la lectura: ");
        String archivoLectura = new String();
        archivoLectura = entrada.nextLine();
        //Lectura de clientes
        miEmpresa.lecturaClientes(archivoLectura);
    }

    //Creación de una cuenta prepago o postpago
    public static void agregarCuenta(Scanner entrada, IEmpresa miEmpresa){
        System.out.println("Ingrese el nombe del cliente y el número de manera consecutiva");
        String nombreCliente = entrada.nextLine();
        long numeroTelefono = entrada.nextInt();
        entrada.nextLine();
        miEmpresa.agregarCuenta(entrada, nombreCliente, numeroTelefono);
    }

    public static void registrarLlamada(Scanner entrada, IEmpresa miEmpresa, Utils modDeUtilidades){
        System.out.println("Ingrese su numero de telefono: ");
        long numero = entrada.nextInt();
        entrada.nextLine(); //Limpiar buffer
        //Identificamos el tipo de cuenta

    }

    public static void agregarRecarga(Scanner entrada, long numeroTelefono, IEmpresa miEmpresa, Utils modDeUtilidades){
         try {
             Prepago cuentaPrepago = modDeUtilidades.buscarCuentaPrepago(numeroTelefono, miEmpresa);
             if(cuentaPrepago != null){
                 //Datos para la recarga
                 System.out.println("Indique la fecha de la recarga en este formato AAAA-MM-DD: ");
                 String fecha = entrada.nextLine();
                 LocalDate fechaRecarga = LocalDate.parse(fecha);

                 System.out.println("Indique el valor de la recarga: ");
                 long valor = entrada.nextInt();
                 entrada.nextLine();
                 Recarga recargaUsuario = new Recarga(fechaRecarga, valor);
                 System.out.println("Recarga realizada");

                 ArrayList<Recarga> recargas = new ArrayList<Recarga>();
                 recargas.add(recargaUsuario);
             }
         } catch (CuentaNoEncontradaException e){
             System.out.println(e.getMessage());
         } catch (Exception e){
             System.out.println(e.getMessage());
         }
    }

    //Por finalizar
    public static void reporteRecargasGenerales(IEmpresa miEmpresa, Utils modDeUtilidades) {
        // 1. Obtenemos todas las cuentas prepago de la empresa
        ArrayList<Prepago> todasLasCuentas = modDeUtilidades.buscarTodasLasCuentasPrepago(miEmpresa);

        if (todasLasCuentas.isEmpty()) {
            System.out.println("No hay cuentas Prepago registradas en la empresa.");
            return;
        }

        for (Prepago cuenta : todasLasCuentas) {
            System.out.println("\nNumero cuenta: " + cuenta.getNumero());

            ArrayList<Recarga> recargasUsuario = cuenta.getRecargas();

            if (recargasUsuario.isEmpty()) {
                System.out.println("Sin recargas registradas.");
            } else {
                for (Recarga r : recargasUsuario) {
                    System.out.println("Fecha: " + r.getFecha() + "Valor: " + r.getValor());
                }
            }
        }
    }
    //Registro de una llamada
}
