package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.model.builder.ProductoBuilder;

public class Producto {
    private String nombre;
    private String id;
    private String categoria;
    private double precio;
    private String imagen;

    public Producto(String nombre,String id,String categoria,double precio,String imagen) {
        this.id = id;
        this.categoria = categoria;
        this.imagen = imagen;
        this.precio = precio;
        this.nombre = nombre;
    }
    public Producto(){}

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getId() {
        return id;
    }

    public static ProductoBuilder builder(){
        return new ProductoBuilder();
    }
}
