package co.uniquindio.marketplacefx.marketplaceapp.factory;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDtoId;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.mappers.PrestamoMappingImpl;
import co.uniquindio.marketplacefx.marketplaceapp.model.MarketPlace;
import co.uniquindio.marketplacefx.marketplaceapp.model.PrestamoObjeto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;
import co.uniquindio.marketplacefx.marketplaceapp.service.IModelFactoryService;
import co.uniquindio.marketplacefx.marketplaceapp.service.IPrestamoMapping;
import co.uniquindio.marketplacefx.marketplaceapp.service.IVendedorDtoCrud;
import co.uniquindio.marketplacefx.marketplaceapp.utils.DataUtil;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.PrimitiveIterator;

public class ModelFactory implements IModelFactoryService, IVendedorDtoCrud {
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
}
