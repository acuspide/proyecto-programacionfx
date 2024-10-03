package co.uniquindio.marketplacefx.marketplaceapp.controller;

import co.uniquindio.marketplacefx.marketplaceapp.factory.ModelFactory;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.model.builder.VendedorBuilder;

import java.util.List;

public class VendedorCrudContoller {
    ModelFactory modelFactory;
    public VendedorCrudContoller(){
        modelFactory = ModelFactory.getInstance();

    }
    public List<VendedorDto> obtenerVendedores() {
        return modelFactory.obtenerListaVendedorDto();
    }
}
