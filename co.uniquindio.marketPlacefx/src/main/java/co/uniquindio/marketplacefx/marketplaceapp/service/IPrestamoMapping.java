package co.uniquindio.marketplacefx.marketplaceapp.service;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Producto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;

import java.util.List;

public interface IPrestamoMapping {
    List<VendedorDto>getVendedores(List<Vendedor> listaVendedor);
    VendedorDto vendedorToVendedorDto(Vendedor vendedor);
    Vendedor vendedorDtoToVendedor(VendedorDto vendedorDto);
    List<ProductoDto>getProductos(List<Producto>listaProducto);
    Producto productoDtoToProducto(ProductoDto producto);
}
