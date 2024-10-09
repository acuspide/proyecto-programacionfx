package co.uniquindio.marketplacefx.marketplaceapp.service;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDtoId;

public interface IProductoCrud {
    boolean agregarProducto(ProductoDto productoDto);
    boolean actualizarProducto(ProductoDto productoDtoOld,ProductoDto productoActualizado);
    boolean eliminarProducto(ProductoDto productoDto);
    boolean buscarProducto(ProductoDtoId productoDtoId);
}
