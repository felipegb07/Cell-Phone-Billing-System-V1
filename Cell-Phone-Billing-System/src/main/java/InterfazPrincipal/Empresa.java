package InterfazPrincipal;

import java.util.ArrayList;

public class Empresa implements IEmpresa{
    private String nombre;
    private ArrayList<Cliente> clientes;

    @Override
    public ArrayList getClientes(){
        return this.clientes;
    }
}
