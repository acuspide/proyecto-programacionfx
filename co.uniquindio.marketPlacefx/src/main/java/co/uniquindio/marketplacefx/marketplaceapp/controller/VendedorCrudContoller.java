package co.uniquindio.marketplacefx.marketplaceapp.controller;

import co.uniquindio.marketplacefx.marketplaceapp.factory.ModelFactory;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDtoId;
import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import co.uniquindio.marketplacefx.marketplaceapp.model.builder.VendedorBuilder;
import javafx.scene.control.TextField;

import java.util.List;

public class VendedorCrudContoller {
    ModelFactory modelFactory;
    public VendedorCrudContoller(){
        modelFactory = ModelFactory.getInstance();

    }
    public List<Usuario> obtenerUsuarios() {
        return modelFactory.obtenerListaUsuario();
    }

    public List<VendedorDto> obtenerVendedores() {
        return modelFactory.obtenerListaVendedorDto();
    }

    public boolean agregarVendedor(VendedorDto vendedorDto) {
        return modelFactory.agregarVendedorDto(vendedorDto);
    }

    public boolean actualizarVendedor(VendedorDto vendedorOld, VendedorDto vendedorActualizado) {
        return modelFactory.actualizarVendedor(vendedorOld,vendedorActualizado);
    }

    public boolean eliminarVendedor(VendedorDto vendedorDto) {
        return modelFactory.eliminarVendedor(vendedorDto);
    }

    public boolean buscarVendedor(VendedorDtoId cedula) {
        return modelFactory.buscarVendedor(cedula);
    }
}
