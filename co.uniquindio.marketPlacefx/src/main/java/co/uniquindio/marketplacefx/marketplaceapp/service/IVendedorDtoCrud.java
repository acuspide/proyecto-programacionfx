package co.uniquindio.marketplacefx.marketplaceapp.service;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDtoId;
import javafx.scene.control.TextField;

public interface IVendedorDtoCrud {
    boolean agregarVendedorDto(VendedorDto vendedorDto);
    boolean actualizarVendedor(VendedorDto vendedorOld,VendedorDto vendedorActualizado);
    boolean eliminarVendedor(VendedorDto vendedorDto);
    boolean buscarVendedor(VendedorDtoId vendedorDtoId);
}
