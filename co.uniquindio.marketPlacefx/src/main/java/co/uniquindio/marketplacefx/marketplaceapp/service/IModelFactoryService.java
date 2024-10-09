package co.uniquindio.marketplacefx.marketplaceapp.service;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDtoId;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDtoId;
import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import javafx.scene.control.TextField;

import java.util.List;

public interface IModelFactoryService {
    List<VendedorDto>obtenerListaVendedorDto();
    List<Usuario> obtenerListaUsuario();
    List<ProductoDto> obtenerProductos();
    boolean agregarVendedorDto(VendedorDto vendedorDto);
    boolean actualizarVendedor(VendedorDto vendedorOld,VendedorDto vendedorActualizado);
    boolean eliminarVendedor(VendedorDto vendedorDto);
    boolean buscarVendedor(VendedorDtoId vendedorDtoId);
    boolean agregarProducto(ProductoDto productoDto);
    boolean actualizarProducto(ProductoDto productoDtoOld,ProductoDto productoActualizado);
    boolean eliminarProducto(ProductoDto productoDto);
    boolean buscarProducto(ProductoDtoId productoDtoId);

}
