package InterfazPrincipal.InterfazEjecucion;

public class Cliente {
    private String direccion;
    private String identificacion;
    private String nombre;
    private String tipoId;
    private Cuenta cuentaCliente;

    /*Constructores*/

    public Cliente(String nombre, String identificacion, String direccion){
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
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
