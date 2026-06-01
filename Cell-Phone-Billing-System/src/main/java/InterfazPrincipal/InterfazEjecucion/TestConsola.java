package InterfazPrincipal.InterfazEjecucion;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class TestConsola{
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese la opción que se quiere implementar");
        System.out.println("1. Ingresar clientes.\n" +
                "2. Agregar nueva cuenta prepago o postapago.\n" +
                "3. Agregar una nueva llamada nacional o internacional\n" +
                "4. Agregar una recarga\n" +
                "5. Reporte de facturación postpago a fin de mes.\n" +
                "6. Reporte de recargas a fin de mes.\n" +
                "7. Guardar la empresa en un archivo como un objeto\n" +
                "8. Cargar un archivo en el objeto empresa\n" +
                "9. Salir" +
                "Opción: ");
        int opcion = entrada.nextInt();
        entrada.nextLine();
        Boolean salirAMenu = false;
        Boolean retorno = false;
        IEmpresa miEmpresa = new Empresa();
        entrada.nextLine();

        do {
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

                    break;

                case 4:
                    System.out.println("---AGREGAR UNA RECARGA---");
                    break;

                case 5:
                    System.out.println("---REPORTE DE FACTURACION POSTPAGO A FIN DE MES---");
                    break;

                case 6:
                    System.out.println("---REPORTE DE RECARGAS A FIN DE MES---");
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

    //Registro de una llamada
    public static void
}
