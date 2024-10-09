package co.uniquindio.marketplacefx.marketplaceapp.factory;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDtoId;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDtoId;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.mappers.PrestamoMappingImpl;
import co.uniquindio.marketplacefx.marketplaceapp.model.MarketPlace;
import co.uniquindio.marketplacefx.marketplaceapp.model.PrestamoObjeto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;
import co.uniquindio.marketplacefx.marketplaceapp.service.IModelFactoryService;
import co.uniquindio.marketplacefx.marketplaceapp.service.IPrestamoMapping;
import co.uniquindio.marketplacefx.marketplaceapp.service.IProductoCrud;
import co.uniquindio.marketplacefx.marketplaceapp.service.IVendedorDtoCrud;
import co.uniquindio.marketplacefx.marketplaceapp.utils.DataUtil;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.PrimitiveIterator;

public class ModelFactory implements IModelFactoryService, IVendedorDtoCrud, IProductoCrud {
    private static ModelFactory modelFactory;
    private MarketPlace marketPlace;
    PrestamoObjeto prestamoObjeto;
    IPrestamoMapping mapper;



    public static ModelFactory getInstance(){
        if(modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    public ModelFactory(){
        mapper = new PrestamoMappingImpl();
        prestamoObjeto = DataUtil.inicializarDatos();

    }

    @Override
    public List<VendedorDto> obtenerListaVendedorDto() {
        return mapper.getVendedores(prestamoObjeto.getListaVendedores());
    }

    @Override
    public List<Usuario> obtenerListaUsuario() {
        return prestamoObjeto.getListaUsuarios();
    }

    @Override
    public List<ProductoDto> obtenerProductos() {
        return mapper.getProductos(prestamoObjeto.getListaProductos());
    }


    @Override
    public boolean agregarVendedorDto(VendedorDto vendedorDto) {
        return prestamoObjeto.agregarVendedor(mapper.vendedorDtoToVendedor(vendedorDto));

    }

    @Override
    public boolean actualizarVendedor(VendedorDto vendedorOld, VendedorDto vendedorActualizado) {
        return prestamoObjeto.actualizarVendedor(mapper.vendedorDtoToVendedor(vendedorOld),mapper.vendedorDtoToVendedor(vendedorActualizado));
    }

    @Override
    public boolean eliminarVendedor(VendedorDto vendedorDto) {
        return prestamoObjeto.eliminarVendedor(mapper.vendedorDtoToVendedor(vendedorDto));
    }

    @Override
    public boolean buscarVendedor(VendedorDtoId vendedorDtoId) {
        return prestamoObjeto.bucarVendedor(vendedorDtoId);
    }

    @Override
    public boolean agregarProducto(ProductoDto productoDto) {
        return prestamoObjeto.agregarProducto(mapper.productoDtoToProducto(productoDto));
    }

    @Override
    public boolean actualizarProducto(ProductoDto productoDtoOld, ProductoDto productoActualizado) {
        return prestamoObjeto.actualizarProducto(mapper.productoDtoToProducto(productoDtoOld),mapper.productoDtoToProducto(productoActualizado));
    }

    @Override
    public boolean eliminarProducto(ProductoDto productoDto) {
        return prestamoObjeto.eliminarProducto(mapper.productoDtoToProducto(productoDto));
    }

    @Override
    public boolean buscarProducto(ProductoDtoId productoDtoId) {
        return prestamoObjeto.buscarProducto(productoDtoId);
    }
}