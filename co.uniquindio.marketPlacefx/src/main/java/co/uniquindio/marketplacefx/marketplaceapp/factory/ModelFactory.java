package co.uniquindio.marketplacefx.marketplaceapp.factory;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.mappers.PrestamoMappingImpl;
import co.uniquindio.marketplacefx.marketplaceapp.model.PrestamoObjeto;
import co.uniquindio.marketplacefx.marketplaceapp.service.IModelFactoryService;
import co.uniquindio.marketplacefx.marketplaceapp.service.IPrestamoMapping;
import co.uniquindio.marketplacefx.marketplaceapp.utils.DataUtil;

import java.util.List;
import java.util.PrimitiveIterator;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
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
}
