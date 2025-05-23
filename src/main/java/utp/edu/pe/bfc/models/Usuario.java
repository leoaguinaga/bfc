package utp.edu.pe.bfc.models;


import utp.edu.pe.bfc.models.enums.Tipo;

public class Usuario {
    private int usuarioId;
    private String nombreCompleto;
    private String correo;
    private String contrasena;
    private Tipo tipo;
    private String telefono;
    private String direccion;

    public Usuario() {
    }

    public Usuario(String nombreCompleto, String correo, String contrasena, Tipo tipo, String telefono, String direccion) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Usuario(int usuarioId, String nombreCompleto, String correo, String contrasena, Tipo tipo, String telefono, String direccion) {
        this.usuarioId = usuarioId;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", tipo=" + tipo +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
