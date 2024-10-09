package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDtoId;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class PrestamoObjeto {
    private List<Vendedor>listaVendedores = new ArrayList<>();
    private List<Usuario>listaUsuarios = new ArrayList<>();

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public boolean agregarVendedor(Vendedor vendedor) {
        Vendedor vendedorEncontrado = obtenerVendedor(vendedor.getCedula());
        if(vendedorEncontrado == null){
            listaVendedores.add(vendedor);
            listaUsuarios.add(vendedor.getUsuario());
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
        Vendedor vendedor = obtenerVendedor(vendedorOld.getCedula());
        Vendedor vendedorMod= obtenerVendedor(vendedorActualizado.getCedula());
        if(vendedor!=null){
            if(vendedor.getCedula().equals(vendedorActualizado.getCedula()) || vendedorMod==null){
                int index=getIndex(vendedorOld.getCedula());
                listaVendedores.set(index,vendedorActualizado);
                listaUsuarios.set(index,vendedorActualizado.getUsuario());
                return true;
            }else{return false;}
        }else{return false;}
    }
    public int getIndex(String cedula) {
        int index=0;
        for(Vendedor v:listaVendedores) {
            if (v.equals(cedula)) {
                index = listaVendedores.indexOf(v);
                break;
            }
        }

        return index;
    }


    public boolean eliminarVendedor(Vendedor vendedor) {
        Vendedor vendedorExisten = obtenerVendedor(vendedor.getCedula());
        if (vendedorExisten != null){
            for (Vendedor vendedor1: listaVendedores){
                if(vendedor1.getCedula().equalsIgnoreCase(vendedor.getCedula())){
                    listaVendedores.remove(vendedor1);
                    listaUsuarios.remove(vendedor1.getUsuario());
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
