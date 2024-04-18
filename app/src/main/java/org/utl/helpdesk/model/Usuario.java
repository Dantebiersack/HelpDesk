package org.utl.helpdesk.model;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String contrasenia;
    private String lastConection ;
    private String token;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getLastConection() {
        return lastConection;
    }

    public void setLastConection(String lastConection) {
        this.lastConection = lastConection;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
}
}
