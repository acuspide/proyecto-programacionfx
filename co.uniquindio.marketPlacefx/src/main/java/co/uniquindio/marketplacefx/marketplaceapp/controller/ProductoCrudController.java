package co.uniquindio.marketplacefx.marketplaceapp.controller;

import co.uniquindio.marketplacefx.marketplaceapp.factory.ModelFactory;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDtoId;

import java.util.List;

public class ProductoCrudController {
    ModelFactory modelFactory;
    public ProductoCrudController (){
        modelFactory = ModelFactory.getInstance();

    }

    public List<ProductoDto> obtenerProductos(){
        return modelFactory.obtenerProductos();
    }

    public boolean agregarVendedor(ProductoDto productoDto) {
        return modelFactory.agregarProducto(productoDto);
    }

    public boolean actualizarProducto(ProductoDto productoDtoOld, ProductoDto productoActualizado) {
        return modelFactory.actualizarProducto(productoDtoOld,productoActualizado);
    }

    public boolean eliminarProducto(ProductoDto productoDto) {
        return modelFactory.eliminarProducto(productoDto);
    }

    public boolean buscarProducto(ProductoDtoId productoDtoId) {
        return modelFactory.buscarProducto(productoDtoId);
    }
}
