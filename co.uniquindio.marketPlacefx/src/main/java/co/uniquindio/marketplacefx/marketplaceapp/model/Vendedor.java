package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.controller.VendedorCrudContoller;
import co.uniquindio.marketplacefx.marketplaceapp.model.builder.VendedorBuilder;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Persona {
    private List<Vendedor>vendedoresAliados = new ArrayList<>();
    private List<Producto>listaProductos = new ArrayList<>();

    public Vendedor(){
    }

    public Vendedor(String nombre, String apellido, String direccion, String cedula, Usuario usuario) {
        super(nombre, apellido,cedula, direccion, usuario);
    }

    public static VendedorBuilder builder(){
        return new VendedorBuilder();
    }
}
