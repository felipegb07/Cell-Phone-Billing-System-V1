package InterfazPrincipal.InterfazEjecucion;

import java.io.*;

public class ManejoArchivos {

    public void serializarDatosEnSistema(IEmpresa miEmpresa, String archivo){
        System.out.println("Ingrese la ruta del archivo: ");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(miEmpresa);
            System.out.println("Objeto Empresa serializado correctamente en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al serializar: " + e.getMessage());
        }
    }

    public IEmpresa cargarDatosDelSistema(IEmpresa miEmpresa, String archivo){
        miEmpresa = null; //eliminamos todo lol
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (IEmpresa) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar: " + e.getMessage());
            return null;
        }
    }
}
