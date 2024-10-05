package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDtoId;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class PrestamoObjeto {
    private List<Vendedor>listaVendedores = new ArrayList<>();

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public boolean agregarVendedor(Vendedor vendedor) {
        Vendedor vendedorEncontrado = obtenerVendedor(vendedor.getCedula());
        if(vendedorEncontrado == null){
            listaVendedores.add(vendedor);
            return true;
        }else{
            return false;
        }

    }

    private Vendedor obtenerVendedor(String cedula) {
        Vendedor vendedor = null;
        for(Vendedor vendedo1 : listaVendedores){
            if(vendedo1.getCedula().equalsIgnoreCase(cedula)){
                vendedor = vendedo1;
                break;
            }
        }
        return vendedor;
    }

    public boolean actualizarVendedor(Vendedor vendedorOld,Vendedor vendedorActualizado) {
        int index=0;
        Vendedor vendedor = obtenerVendedor(vendedorOld.getCedula());
        Vendedor vendedorMod= obtenerVendedor(vendedorActualizado.getCedula());
        if(vendedor!=null){
            for(Vendedor v:listaVendedores) {
                if (v.equals(vendedorOld.getCedula())) {
                    index = listaVendedores.indexOf(v);
                    break;
                }
            }
        }else{return false;}
        if(vendedor.getCedula().equals(vendedorActualizado.getCedula()) || vendedorMod==null){
            listaVendedores.set(index,vendedorActualizado);
            return true;
        }else{return false;}
    }

    private Vendedor actualizarVendedorBuilder(Vendedor vendedorActualizado) {
        return  Vendedor.builder()
                .nombre(vendedorActualizado.getNombre())
                .apellido(vendedorActualizado.getApellido())
                .cedula(vendedorActualizado.getCedula())
                .direccion(vendedorActualizado.getDireccion())
                .usuario(vendedorActualizado.getUsuario())
                .contrasena(vendedorActualizado.getContrasena())
                .build();
    }

    public boolean eliminarVendedor(Vendedor vendedor) {
        Vendedor vendedorExisten = obtenerVendedor(vendedor.getCedula());
        if (vendedorExisten != null){
            for (Vendedor vendedor1: listaVendedores){
                if(vendedor1.getCedula().equalsIgnoreCase(vendedor.getCedula())){
                    listaVendedores.remove(vendedor1);
                    return true;

                }

            }
        }

        return false;
    }

    public boolean bucarVendedor(VendedorDtoId vendedorDtoId) {
        for (Vendedor vendedor: listaVendedores){
            if (vendedor.getCedula().equalsIgnoreCase(vendedorDtoId.cedula())){
                return true;
            }
        }
        return false;
    }
}
