package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.controller.VendedorCrudContoller;
import co.uniquindio.marketplacefx.marketplaceapp.model.builder.VendedorBuilder;

public class Vendedor {
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String usuario;
    private String contrasena;

    public Vendedor(){
    }
    public Vendedor(String nombre,
                    String apellido,
                    String cedula,
                    String direccion,
                    String usuario,
                    String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public static VendedorBuilder builder(){
        return new VendedorBuilder();
    }
}
