package co.uniquindio.marketplacefx.marketplaceapp.model.builder;

import co.uniquindio.marketplacefx.marketplaceapp.model.Producto;

public class ProductoBuilder {
    protected String nombre;
    protected String id;
    protected String categoria;
    protected double precio;
    protected String imagen;

    public ProductoBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoBuilder imagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    public ProductoBuilder precio(double precio) {
        this.precio = precio;
        return this;
    }

    public ProductoBuilder categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProductoBuilder id(String id) {
        this.id = id;
        return this;
    }
    public Producto build(){
        return new Producto(nombre,id,categoria,precio,imagen);
    }
}
