package co.uniquindio.marketplacefx.marketplaceapp.mapping.mappers;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Producto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;
import co.uniquindio.marketplacefx.marketplaceapp.service.IPrestamoMapping;

import java.util.ArrayList;
import java.util.List;

public class PrestamoMappingImpl implements IPrestamoMapping {
    @Override
    public List<VendedorDto> getVendedores(List<Vendedor> listaVendedor) {
        if(listaVendedor == null){
            return null;
        }

        List<VendedorDto>listaVendedorDto = new ArrayList<VendedorDto>(listaVendedor.size());
        for (Vendedor vendedor: listaVendedor){
            listaVendedorDto.add(vendedorToVendedorDto(vendedor));
        }
        return listaVendedorDto;
    }

    @Override
    public VendedorDto vendedorToVendedorDto(Vendedor vendedor) {
        return new VendedorDto(vendedor.getNombre(),
                vendedor.getApellido(),
                vendedor.getCedula(),
                vendedor.getDireccion(),
                vendedor.getUsuario());
    }

    @Override
    public Vendedor vendedorDtoToVendedor(VendedorDto vendedorDto) {
        return new Vendedor(vendedorDto.nombre(),
                vendedorDto.apellido(),
                vendedorDto.cedula(),
                vendedorDto.direccion(),
                vendedorDto.usuario());
    }

    @Override
    public List<ProductoDto> getProductos(List<Producto> listaProducto) {
        if(listaProducto == null){
            return null;
        }
        List<ProductoDto>listaProductoDto = new ArrayList<ProductoDto>(listaProducto.size());
        for (Producto producto : listaProducto){
            listaProductoDto.add(productoToProductoDto(producto));
        }
        return listaProductoDto;
    }

    @Override
    public Producto productoDtoToProducto(ProductoDto producto) {
        return new Producto(producto.nombre(),
                producto.id(),
                producto.categoria(),
                producto.precio(),
                producto.imagen());
    }

    private ProductoDto productoToProductoDto(Producto producto) {
        return new ProductoDto(producto.getNombre(),
                producto.getId(),
                producto.getCategoria(),
                producto.getPrecio(),
                producto.getImagen());
    }


}
