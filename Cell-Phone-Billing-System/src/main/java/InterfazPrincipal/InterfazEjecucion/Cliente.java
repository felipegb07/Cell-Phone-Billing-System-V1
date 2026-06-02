package InterfazPrincipal.InterfazEjecucion;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String direccion;
    private String identificacion;
    private String nombre;
    private String tipoId;
    private Cuenta cuentaCliente;

    /*Constructores*/
    public Cliente(){}

    public Cliente(String nombre, String identificacion, String direccion){
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(identificacion, cliente.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion);
    }

    public Cliente(String direccion, String identificacion, String nombre, String tipoId, Cuenta cuentaCliente) {
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.tipoId = tipoId;
        this.cuentaCliente = cuentaCliente;
    }

    /*Getter y setter*/
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public Cuenta getCuentaCliente() {
        return cuentaCliente;
    }

    public void setCuentaCliente(Cuenta cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
    }
}
